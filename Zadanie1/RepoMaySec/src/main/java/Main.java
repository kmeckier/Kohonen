import java.io.IOException;
import java.util.List;

public class Main {

    public static final String INPUT_FILE = "test.png";
    public static final Integer NUMBER_OF_NEURONS = 100;
    public static final Integer FRAME_SIZE = 4;
    public static final Double TRAINING_STEP = 0.001;
    public static final Integer PATTERNS_COUNT = 100;
    public static final Integer AGES_COUNT = 100;

    public static void main(String... args) throws IOException {

        Integer minimalWinnerCounter = 1;
        Integer bitsPerWeight = 8;

        LoaderFile imageLoader = new LoaderFile();
        int[][] originalImage = imageLoader.loadImage(INPUT_FILE);

        KohonenNetwork kohonenNetwork = new KohonenNetwork(
                NUMBER_OF_NEURONS,
                (int) Math.pow(FRAME_SIZE, 2),
                TRAINING_STEP,
                minimalWinnerCounter
        );

        GeneratorRandomPixels generatorRandomPixels = new GeneratorRandomPixels(originalImage,
                512,
                FRAME_SIZE);

        List<PixelFrame> randomPixelFramesList = generatorRandomPixels.generatePixelFramesList(PATTERNS_COUNT);

        for (int i = 0; i < AGES_COUNT; i++) {
            for (PixelFrame pixelFrame : randomPixelFramesList) {
                kohonenNetwork.processPixelFrame(pixelFrame);
            }
        }

        kohonenNetwork.deleteDeadNeurons();

        ConverterFile imageConverter = new ConverterFile(originalImage, FRAME_SIZE, 512);
        PixelFrame[][] pixelFrames = imageConverter.convertImageToPixelFrames();

        Compression compression = new Compression(kohonenNetwork);
        CompressedFrame[][] compressedFrames = compression.compress(pixelFrames);

        Decompression decompression = new Decompression(kohonenNetwork, compressedFrames, FRAME_SIZE);
        int[][] decompressedImage = decompression.decompressImage();

        imageLoader.saveImageToFile(decompressedImage, "result.png", 512);

        KohonenOutputs kohonenOutputs = new KohonenOutputs();

        double compressionRate = kohonenOutputs.calculateCompressionRate(
                512,
                FRAME_SIZE,
                compressedFrames,
                bitsPerWeight,
                NUMBER_OF_NEURONS
        );
        double psnr = kohonenOutputs.calculatePSNR(originalImage, decompressedImage);

        System.out.println("Współczynnik kompresji:  " + compressionRate);
        System.out.println("Wartość miary PSNR: " + psnr);
    }
}
