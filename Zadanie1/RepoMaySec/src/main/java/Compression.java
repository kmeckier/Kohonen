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
}
