import java.util.Arrays;
import java.util.Comparator;

public class KohonenOutputs {

    public double calculateCompressionRate(int imageWidthHeight, int frameWidthHeight,
                                           CompressedFrame[][] compressedFrames, int bitsPerWeight, int neuronsNumber) {
        double originalImageBitsNumber = Math.pow(imageWidthHeight, 2) * 8;
        double compressedImageBitsNumber = Math.pow(frameWidthHeight, 2) * neuronsNumber * bitsPerWeight;

        int neuronIndexBitNumber = Helper.minimalNumberOfBits(neuronsNumber);
        compressedImageBitsNumber += neuronIndexBitNumber * compressedFrames.length * compressedFrames.length;

        double maxBrightnessValue = Arrays.stream(compressedFrames)
                .flatMap(Arrays::stream)
                .max(Comparator.comparing(CompressedFrame::getBrightness))
                .get()
                .getBrightness();

        int bitsNumberForBrightness = Helper.minimalNumberOfBits(maxBrightnessValue);
        compressedImageBitsNumber += compressedFrames.length * compressedFrames.length * bitsNumberForBrightness;

        return originalImageBitsNumber / compressedImageBitsNumber;
    }

    public double calculatePSNR(int[][] originalImage, int[][] decompressedImage) {
        double mse = 0;

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                mse += Math.pow(originalImage[i][j] - decompressedImage[i][j], 2);
            }
        }

        mse /= originalImage.length * originalImage.length;
        return 10 * Math.log10(Math.pow(255, 2) / mse);
    }
}
