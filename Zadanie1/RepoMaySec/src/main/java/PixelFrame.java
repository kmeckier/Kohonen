public class PixelFrame {
    private double[] pixels;
    private double brightness;

    public PixelFrame(double[] pixels) {
        this.pixels = pixels;
        this.brightness = 0;
    }

    public PixelFrame(double[] pixels, double brightness) {
        this.pixels = pixels;
        this.brightness = brightness;
    }


    public double[] getPixels() {
        return pixels;
    }

    public void setPixels(double[] pixels) {
        this.pixels = pixels;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public int getAvg() {
        int sum = 0;
        for (int i = 0; i < pixels.length; i++) {
            sum += (int) pixels[i];
        }
        return sum / pixels.length;
    }
}
