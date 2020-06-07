
public class Decompression {
    private KohonenNetwork kohonenNetwork;
    private int frameWidthHeight;
    private int[][] decompressedImage;
    private CompressedFrame[][] compressedFrames;

    public Decompression(KohonenNetwork kohonenNetwork, CompressedFrame[][] compressedFrames, int frameWidthHeight) {
        this.kohonenNetwork = kohonenNetwork;
        this.frameWidthHeight = frameWidthHeight;
        this.compressedFrames = compressedFrames;
        this.decompressedImage = new int[compressedFrames.length * frameWidthHeight][compressedFrames[0].length * frameWidthHeight];
    }

    public int[][] decompressImage() {
        int imageStartPixelPositionX = 0, imageStartPixelPositionY;
        for (int compressedFramePositionX = 0; compressedFramePositionX < this.compressedFrames.length; compressedFramePositionX++) {
            imageStartPixelPositionY = 0;

            for (int compressedFramePositionY = 0; compressedFramePositionY < this.compressedFrames[0].length; compressedFramePositionY++) {

                CompressedFrame compressedFrame = this.compressedFrames[compressedFramePositionX][compressedFramePositionY];
                int winningNeuronIndex = compressedFrame.getWinningNeuronIndex();
                double[] pixels = this.kohonenNetwork.getNeuronWeights(winningNeuronIndex);
                int[] denormalizedPixels = Normalization.denormalizeVector(pixels, compressedFrame.getBrightness());
                int[] pixelsInt = new int[pixels.length];
                for (int i = 0; i < pixels.length; i++) {
                    pixelsInt[i] += (int) Math.round(pixels[i]);
                }
                this.savePixelsToImage(denormalizedPixels, imageStartPixelPositionX, imageStartPixelPositionY);

                imageStartPixelPositionY += this.frameWidthHeight;
            }
            imageStartPixelPositionX += this.frameWidthHeight;
        }
        return this.decompressedImage.clone();
    }

    private void savePixelsToImage(int[] denormalizedPixels, int imageStartPixelPositionX, int imageStartPixelPositionY) {
        for (int imagePixelPositionX = imageStartPixelPositionX; imagePixelPositionX < imageStartPixelPositionX + frameWidthHeight; imagePixelPositionX++) {
            int denormalizedPixelsVectorIndex = 0;

            for (int imagePixelPositionY = imageStartPixelPositionY; imagePixelPositionY < imageStartPixelPositionY + frameWidthHeight; imagePixelPositionY++) {
                int pixelValue = denormalizedPixels[denormalizedPixelsVectorIndex];
                decompressedImage[imagePixelPositionX][imagePixelPositionY] = pixelValue;
                denormalizedPixelsVectorIndex++;
            }

        }
    }
}
