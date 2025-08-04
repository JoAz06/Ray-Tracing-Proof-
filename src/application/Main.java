package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    	double x = 0;
        double y = 0;
        double z = 0;
        double angXZ = 0;
        double angXY = 0;
        
        double tempx = x;
        double tempy = y;
        double tempz = z;
        double tempangXZ = angXZ;
        double tempangXY = angXY;
        
        
        int angSpeed = 5;
        int movSpeed = 5;
        int universeX = 1000;
        int universeY = 1000;
        int universeZ = 1000;
        		
    @Override
    public void start(Stage primaryStage) {
        int width = 800;
        int height = 800;
        Pane primaryPane = new Pane();
        Scene primaryScene = new Scene(primaryPane,height,width);
        System.out.println("\tX: "+x+"\tY: "+y+"\tZ: "+z+"\t\tangXY "+angXY+"\tangXZ"+angXZ);
        primaryScene.setOnKeyPressed(event -> {
            if(event.getCode().toString() =="UP" ) {
            	if(angXY < 90) {
            		angXY += angSpeed;
            	}
            }
            if(event.getCode().toString() =="DOWN" ) {
            	if(angXY > -90) {
            		angXY -= angSpeed;
            	}
            }
            if(event.getCode().toString() =="LEFT" ) {
            	angXZ += angSpeed;
            }
            if(event.getCode().toString() =="RIGHT" ) {
            	angXZ += (360 - angSpeed);
            }
            
            angXZ %= 360;
            
            if(event.getCode().toString() =="SPACE" ) {
            	if(y < universeY) {
            		y += movSpeed;
            	}
            }
            if(event.getCode().toString() =="C" ) {
            	if(y > 0) {
            		y -= movSpeed;
            	}
            }
            if(event.getCode().toString() =="W" ) {
            	tempx = x;
                tempz = z;
                
                tempx += (Math.round(movSpeed * Math.cos(Math.toRadians(angXZ)) * 1000)/1000);
                tempz += (Math.round(movSpeed * Math.sin(Math.toRadians(angXZ)) * 1000)/1000);
                
                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
                	x = tempx;
                	z = tempz;
                }
                
            }
            if(event.getCode().toString() =="S" ) {
            	tempx = x;
                tempz = z;
                
                tempx -= (Math.round(movSpeed * Math.cos(Math.toRadians(angXZ)) * 1000)/1000);
                tempz -= (Math.round(movSpeed * Math.sin(Math.toRadians(angXZ)) * 1000)/1000);
                
                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
                	x = tempx;
                	z = tempz;
                }
                
            }
            if(event.getCode().toString() =="A" ) {
            	tempx = x;
                tempz = z;
                tempangXZ = angXZ + 90;
                tempangXZ %= 360; 	
                
                tempx += (Math.round(movSpeed * Math.cos(Math.toRadians(tempangXZ)) * 1000)/1000);
                tempz += (Math.round(movSpeed * Math.sin(Math.toRadians(tempangXZ)) * 1000)/1000);
                
                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
                	x = tempx;
                	z = tempz;
                }
            }
            
            if(event.getCode().toString() =="D" ) {
            	tempx = x;
                tempz = z;
                tempangXZ = angXZ - 90;
                tempangXZ %= 360;
                
                tempx += (Math.round(movSpeed * Math.cos(Math.toRadians(tempangXZ)) * 1000)/1000);
                tempz += (Math.round(movSpeed * Math.sin(Math.toRadians(tempangXZ)) * 1000)/1000);
                
                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
                	x = tempx;
                	z = tempz;
                }
                //
            }
            
            System.out.println(event.getCode()+"\tX: "+x+"\tY: "+y+"\tZ: "+z+"\t\tangXY "+angXY+"\tangXZ "+angXZ);
        });
        
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}