/**
 * Auto Generated Java Class.
 */

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import static java.lang.Math.*;

public class SeamCarver {
    int w,h;
    Picture pic;
    public SeamCarver(Picture picture){
        this.pic=picture;
        w=picture.width();
        h=picture.height();
    }
    public Picture picture(){
        return pic;
    }
    public int width(){
        return w;
    }
    public int height(){
        return h;
    }
    private void validateVertices(int x,int y){
        if ((x<0 || x>=w) || (y<0 || y>=h))
            throw new IndexOutOfBoundsException("vertex is not in range ");
    }
    public double energy(int x,int y){
        validateVertices(x,y);
        if(x==0 || y==0 || x==w-1 || y==h-1 )
            return 195075;
        else{
            Color color1=pic.get(x+1,y);
            Color color2=pic.get(x-1,y);
            Color color3=pic.get(x,y-1);
            Color color4=pic.get(x,y+1);
            int xGrad=0,yGrad=0;
            xGrad+=(color1.getRed()-color2.getRed())*(color1.getRed()-color2.getRed());
            xGrad+=(color1.getGreen()-color2.getGreen())*(color1.getGreen()-color2.getGreen());
            xGrad+=(color1.getBlue()-color2.getBlue())*(color1.getBlue()-color2.getBlue());
           
            
            yGrad+=(color3.getRed()-color4.getRed())*(color3.getRed()-color4.getRed());
            yGrad+=(color3.getGreen()-color4.getGreen())*(color3.getGreen()-color4.getGreen());
            yGrad+=(color3.getBlue()-color4.getBlue())*(color3.getBlue()-color4.getBlue());
            return xGrad+yGrad;
        }
    }
    public int[] findHorizontalSeam(){
        double[][] dist = new double[w][h];
        double[][] energyArr = new double[w][h];
        int[][] parent=new int[w][h];
        int i,j;
        double minim;
        for(i=0;i<w;i++){
            for(j=0;j<h;j++){
                dist[i][j]=energy(i,j);
            }
        }
        for(j=0;j<h;j++){
            energyArr[0][j]=dist[0][j];
        }
        for(i=1;i<w;i++){
            for(j=0;j<h;j++){
                double x=dist[i][j];
                if(j==0){
                   minim=min(energyArr[i-1][j+1],energyArr[i-1][j]);
                     if(minim==energyArr[i-1][j+1]){
                        parent[i][j]=1;
                    }
                    else if(minim==energyArr[i-1][j]){
                        parent[i][j]=0;
                    } 
                }
                else if(j==h-1){
                    minim=min(energyArr[i-1][j-1],energyArr[i-1][j]);
                     if(minim==energyArr[i-1][j-1]){
                        parent[i][j]=-1;
                    }
                    else if(minim==energyArr[i-1][j]){
                        parent[i][j]=0;
                    }
                }
                else{
                    minim=min(min(energyArr[i-1][j-1],energyArr[i-1][j]),energyArr[i-1][j+1]);
                    if(minim==energyArr[i-1][j-1]){
                        parent[i][j]=-1;
                    }
                    else if(minim==energyArr[i-1][j]){
                        parent[i][j]=0;
                    }
                    else if(minim==energyArr[i-1][j+1]){
                        parent[i][j]=1;
                    }
                }
                energyArr[i][j]=x+minim;
            }    
        }
        minim=1e9;
        int minpos=0;
        for(j=0;j<h;j++){
            if(energyArr[w-1][j]<minim){
                minim=energyArr[w-1][j];
                minpos=j;
            }    
        }
        int res[]=new int[w];
        j=minpos;
        for(i=w-1;i>=0;i--){
            res[i]=j;
            j=j+parent[i][j];
        }
        return res;
    }
    public int[] findVerticalSeam(){
        double[][] dist = new double[w][h];
        double[][] energyArr = new double[w][h];
        int[][] parent=new int[w][h];
        int i,j;
        double minim;
        for(i=0;i<w;i++){
            for(j=0;j<h;j++){
                dist[i][j]=energy(i,j);
            }
        }
        for(i=0;i<w;i++){
            energyArr[i][0]=dist[i][0];
        }
        for(j=1;j<h;j++){
            for(i=0;i<w;i++){
                double x=dist[i][j];
                if(i==0){
                   minim=min(energyArr[i+1][j-1],energyArr[i][j-1]);
                     if(minim==energyArr[i+1][j-1]){
                        parent[i][j]=1;
                    }
                    else if(minim==energyArr[i][j-1]){
                        parent[i][j]=0;
                    } 
                }
                else if(i==w-1){
                    minim=min(energyArr[i-1][j-1],energyArr[i][j-1]);
                     if(minim==energyArr[i-1][j-1]){
                        parent[i][j]=-1;
                    }
                    else if(minim==energyArr[i][j-1]){
                        parent[i][j]=0;
                    }
                }
                else{
                    minim=min(min(energyArr[i-1][j-1],energyArr[i][j-1]),energyArr[i+1][j-1]);
                    if(minim==energyArr[i-1][j-1]){
                        parent[i][j]=-1;
                    }
                    else if(minim==energyArr[i][j-1]){
                        parent[i][j]=0;
                    }
                    else if(minim==energyArr[i+1][j-1]){
                        parent[i][j]=1;
                    }
                }
                energyArr[i][j]=x+minim;
            }
        }
        minim=1e9;
        int minpos=0;
        for(i=0;i<w;i++){
            if(energyArr[i][h-1]<minim){
                minim=energyArr[i][h-1];
                minpos=i;
            }    
        }
        int res[]=new int[h];
        i=minpos;
        for(j=h-1;j>=0;j--){
            res[j]=i;
            i=i+parent[i][j];
        }
        return res;
    }
    public void removeHorizontalSeam(int[] seam){
        if(h==0)
            throw new IndexOutOfBoundsException("Nothing to carve \n");
        Picture newpic=new Picture(w,h-1);
        int i,j;
        for(i=0;i<w;i++){
            int pos=0;
            for(j=0;j<h;j++){
                if(j==seam[i]) continue;
                newpic.set(i,pos,pic.get(i,j));
                pos++;
            }
        }    
        pic=newpic;
        newpic=null;
        h--;
    }
    public void removeVerticalSeam(int[] seam){
        if(w==0)
            throw new IndexOutOfBoundsException("Nothing to carve \n");
        Picture newpic=new Picture(w-1,h);
        int i,j;
        for(i=0;i<h;i++){
            int pos=0;
            for(j=0;j<w;j++){
                if(j==seam[i]) continue;
                newpic.set(pos,i,pic.get(j,i));
                pos++;
            }
        }    
        pic=newpic;
        newpic=null;    
        w--;
    }
}