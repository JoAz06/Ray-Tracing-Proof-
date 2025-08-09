	package application;
	import java.util.Timer;
	import javafx.application.Application;
	import javafx.application.Platform;
	import javafx.scene.Scene;
	import javafx.scene.image.ImageView;
	import javafx.scene.image.PixelWriter;
	import javafx.scene.image.WritableImage;
	import javafx.scene.layout.Pane;
	import javafx.scene.paint.Color;
	import javafx.stage.Stage;
	
	public class Main extends Application {
	    	double posX = 0;
	        double posY = 500;
	        double posZ = 500;
	        double angXZ = 0;
	        double angXY = 0;
	        
	        double tempx = posX;
	        double tempy = posY;
	        double tempz = posZ;
	        double tempangXZ = angXZ;
	        double tempangXY = angXY;
	        
	        double angSpeed = 0.01;
	        int movSpeed = 5;
	        int universeX = 1000;
	        int universeY = 1000;
	        int universeZ = 1000;
	        
	        int width = 800;
	        int height = 800;
	        int FOV = 180;
	        double dAng = Math.toRadians(FOV)/width;
	        
	        
	        //temperary stuff, should be used as objects
	        //Cirlce 1
	        double[] c1Origin = {500 , 500 , 500};
	        double c1Radius = 1;
	        //Rays
	        double rayXZ;
	        double rayXY;
	        double[] D = new double[3];
	        
	        
	        
	    @Override
	    public void start(Stage primaryStage) {
	        
	        Pane primaryPane = new Pane();
	        WritableImage image = new WritableImage(width,height);
	        PixelWriter pixelWriter = image.getPixelWriter();
	        ImageView imageView = new ImageView(image);
	        primaryPane.getChildren().addAll(imageView);
	        Scene primaryScene = new Scene(primaryPane,width,height);
	        System.out.println("\tX: "+posX+"\tY: "+posY+"\tZ: "+posZ+"\t\tangXY "+angXY+"\tangXZ"+angXZ);
	        primaryScene.setOnKeyPressed(event -> {
	        	if(event.getCode().toString().equals("ESCAPE") ) {
	            	Platform.exit();
	            }
	        	if(event.getCode().toString().equals("DOWN") ) {
	            	if(angXY < 90) {
	            		angXY += angSpeed;
	            	}
	            }
	            if(event.getCode().toString().equals("UP") ) {
	            	if(angXY > -90) {
	            		angXY -= angSpeed;
	            	}
	            }
	            if(event.getCode().toString().equals("RIGHT") ) {
	            	angXZ += angSpeed;
	            }
	            if(event.getCode().toString().equals("LEFT") ) {
	            	angXZ += (360 - angSpeed);
	            }
	            
	            angXZ %= 360;
	            
	            if(event.getCode().toString().equals("SPACE") ) {
	            	if( posY < universeY) {
	            		posY += movSpeed;
	            	}
	            }
	            if(event.getCode().toString().equals("C") ) {
	            	if( posY > 0) {
	            		posY -= movSpeed;
	            	}
	            }
	            if(event.getCode().toString().equals("W") ) {
	            	tempx = posX;
	                tempz = posZ;
	                
	                tempx += (Math.round(movSpeed * Math.cos(Math.toRadians(angXZ)) * 1000)/1000);
	                tempz += (Math.round(movSpeed * Math.sin(Math.toRadians(angXZ)) * 1000)/1000);
	                
	                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
	                	posX = tempx;
	                	posZ = tempz;
	                }
	                
	            }
	            if(event.getCode().toString().equals("S") ) {
	            	tempx = posX;
	                tempz = posZ;
	                
	                tempx -= (Math.round(movSpeed * Math.cos(Math.toRadians(angXZ)) * 1000)/1000);
	                tempz -= (Math.round(movSpeed * Math.sin(Math.toRadians(angXZ)) * 1000)/1000);
	                
	                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
	                	posX = tempx;
	                	posZ = tempz;
	                }
	                
	            }
	            if(event.getCode().toString().equals("A") ) {
	            	tempx = posX;
	                tempz = posZ;
	                tempangXZ = angXZ + 90;
	                tempangXZ %= 360; 	
	                
	                tempx += (Math.round(movSpeed * Math.cos(Math.toRadians(tempangXZ)) * 1000)/1000);
	                tempz += (Math.round(movSpeed * Math.sin(Math.toRadians(tempangXZ)) * 1000)/1000);
	                
	                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
	                	posX = tempx;
	                	posZ = tempz;
	                }
	            }
	            
	            if(event.getCode().toString().equals("D") ) {
	            	tempx = posX;
	                tempz = posZ;
	                tempangXZ = angXZ - 90;
	                tempangXZ %= 360;
	                
	                tempx += (Math.round(movSpeed * Math.cos(Math.toRadians(tempangXZ)) * 1000)/1000);
	                tempz += (Math.round(movSpeed * Math.sin(Math.toRadians(tempangXZ)) * 1000)/1000);
	                
	                if((tempx >= 0 && tempx <= universeX) && (tempz >= 0 && tempz <= universeZ)) {
	                	posX = tempx;
	                	posZ = tempz;
	                }
	                
	            }
	            //long startTime = System.nanoTime();
	        for(int j = 0 ; j < height ; j++) {
	        	for(int i =0 ; i < width ; i++) {
	        		/*
	        		D[0] = Math.cos(Math.toRadians((angXZ - FOV/2) + i*dAng));
	        		D[1] = Math.sin(Math.toRadians((angXY - FOV/2) + j*dAng));
	        		D[2] = Math.sin(Math.toRadians((angXZ - FOV/2) + i*dAng));
	        		*/
	        		// Calculate pixel's angular offset from center
	        		double pixelOffsetH = (i - width/2.0) * dAng;   // horizontal offset
	        		double pixelOffsetV = (j - height/2.0) * dAng;  // vertical offset

	        		// Combine with camera rotation
	        		double totalAzimuth = Math.toRadians(angXZ + pixelOffsetH);
	        		double totalElevation = Math.toRadians(angXY + pixelOffsetV);

	        		// Proper 3D rotation
	        		D[0] = Math.cos(totalElevation) * Math.cos(totalAzimuth);  // X
	        		D[1] = -Math.sin(totalElevation);                           // Y  
	        		D[2] = -Math.cos(totalElevation) * Math.sin(totalAzimuth);  // Z
	        		
	        		double temp = (Math.pow((2*D[0]*(posX - c1Origin[0]) + 2*D[1]*(posY - c1Origin[1]) + 2*D[2]*(posZ - c1Origin[2])), 2) - 4*(Math.pow(D[0], 2) + Math.pow(D[1], 2) + Math.pow(D[2], 2))*(Math.pow((posX - c1Origin[0]), 2) + Math.pow((posY - c1Origin[1]), 2) + Math.pow((posZ - c1Origin[2]), 2) - Math.pow(c1Radius, 2)));
	        		
	        		if(temp >= 0) {
	        			pixelWriter.setColor(i, j, Color.WHITE);
	        			
	        		}else {
	        			
	        			pixelWriter.setColor(i, j, Color.BLACK);
	        		}
	        	}
	        } 
	        /*long endTime = System.nanoTime();
	        double elapsedMs = (endTime - startTime) / 1_000_000.0;
	        System.out.println("Time taken: " + elapsedMs + " ms");*/
	            //System.out.println(event.getCode()+"\tX: "+posX+"\tY: "+posY+"\tZ: "+posZ+"\t\tangXY "+angXY+"\tangXZ "+angXZ);
	        });
	        
	        
	        
	        primaryStage.setScene(primaryScene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	        
	    }
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
	}