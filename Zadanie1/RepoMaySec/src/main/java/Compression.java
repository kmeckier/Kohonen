public class Compression {
    private KohonenNetwork kohonenNetwork;

    public Compression(KohonenNetwork kohonenNetwork) {
        this.kohonenNetwork = kohonenNetwork;
    }

    public CompressedFrame[][] compress(PixelFrame[][] pixelFrames) {
        CompressedFrame[][] compressedFrames = new CompressedFrame[pixelFrames.length][pixelFrames[0].length];
        for (int i = 0; i < pixelFrames.length; i++) {
            for (int j = 0; j < pixelFrames[0].length; j++) {
                int winningNeuronIndex = kohonenNetwork.findWinningNeuronIndex(pixelFrames[i][j].getPixels());
                compressedFrames[i][j] = new CompressedFrame(winningNeuronIndex, pixelFrames[i][j].getBrightness());
            }
        }
        return compressedFrames;
    }

    public int[][] fromAvg(PixelFrame[][] pixelFrames, int image_size, int frame_size) {
        int[][] imageFromAvg = new int[image_size][image_size];
        for (int i = 0; i < pixelFrames.length; i++) {
            for (int j = 0; j < pixelFrames[0].length; j++) {
                int pixelFrameAvg = pixelFrames[i][j].getAvg();
                for (int y = 0; y < frame_size; y++) {
                    for (int x = 0; x < frame_size; x++) {
                        int tempX = i * frame_size + x;
                        int tempY = j * frame_size + y;
                        imageFromAvg[tempX][tempY] = pixelFrameAvg;
                    }
                }
            }
        }

        return imageFromAvg;
    }
}
