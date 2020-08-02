class CalcExpression implements Runnable{
    private final float[] arrayToCalc;

    public CalcExpression(float[] arrayToCalc) {
        this.arrayToCalc = arrayToCalc;
    }

    @Override
    public void run() {
        for (int i = 0; i < arrayToCalc.length; i++) {
            arrayToCalc[i] = (float)(arrayToCalc[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}