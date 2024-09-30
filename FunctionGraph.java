import processing.core.*;

public class FunctionGraph extends PApplet {
    float a = 2;
    int strokeWeight = 2;
    int strokeColor;
    int strokeStyle = 0; // 0 - solid, 1 - dashed, 2 - dotted

    public static void main(String[] args) {
        PApplet.main("FunctionGraph");
    }

    public void settings() {
        size(1200, 800);
    }

    public void setup() {
        strokeColor = color(255, 0, 0); // Start with red color
        textAlign(LEFT, TOP);
    }

    public void draw() {
        background(255);
        drawAxes();
        drawFunction();
        drawAuthorInfo();
    }

    void drawAxes() {
        stroke(0);
        strokeWeight(1);
        line(0, height/2, width, height/2); // x-axis
        line(width/2, 0, width/2, height); // y-axis
    }

    void drawFunction() {
        stroke(strokeColor);
        strokeWeight(strokeWeight);
        
        float prevX = -width/2;
        float prevY = calculateY(-width/2);
        
        for (float x = -width/2 + 1; x <= width/2; x++) {
            float y = calculateY(x);
            if (Float.isNaN(y)) continue;
            
            if (strokeStyle == 0) {
                line(x + width/2, -y + height/2, prevX + width/2, -prevY + height/2);
            } else if (strokeStyle == 1 && (int)x % 10 < 5) {
                line(x + width/2, -y + height/2, prevX + width/2, -prevY + height/2);
            } else if (strokeStyle == 2 && (int)x % 5 == 0) {
                point(x + width/2, -y + height/2);
            }
            
            prevX = x;
            prevY = y;
        }
    }

    float calculateY(float x) {
        float y = a * (float)Math.asin(a / sqrt(a*a + x*x));
        return y * 50; // Scale for better visibility
    }

    void drawAuthorInfo() {
        fill(0);
        text("Автор: Ваше_Прізвище", 10, 10);
        text("Варіант: Номер_Варіанту", 10, 30);
    }

    public void mousePressed() {
        strokeColor = color(random(255), random(255), random(255));
        strokeWeight = (int)random(1, 5);
        strokeStyle = (int)random(3);
    }
}
