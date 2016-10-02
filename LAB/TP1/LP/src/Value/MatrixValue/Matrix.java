/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import java.util.Random;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class Matrix {
    private int rows;
    private int cols;
    private int matrix[][];

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setValue(int r, int c, int v){
        this.matrix[r][c] = v;
    }
    
     
    public void show(){}
    
    public int size(){
        return rows * cols;
    }
    
    public int rows(){
        return rows;
    }
    
    public int cols(){
        return cols;
    }
    
    public int value(int r, int c){
        return matrix[r][c];
    }
    
    public Matrix opposed(){
        Matrix aux = new Matrix(rows,cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                aux.setValue(i, j, -1*(matrix[i][j]));
            }
        }
        return aux;
    }
    
    public Matrix transposed(){
        Matrix aux = new Matrix(rows,cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                aux.setValue(j, i, matrix[i][j]);
            }
        }
        return aux;
    }
    
    public Matrix sum(Matrix m){
        Matrix aux = new Matrix(rows,cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                aux.setValue(i,j,this.matrix[i][j] + m.value(i, j));
            }
        }
        return aux;
    }
    
    public Matrix mul(Matrix m){
        Matrix aux = new Matrix(rows,cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                for(int k = 0; k < cols; k++){
                    aux.setValue(i,j,this.matrix[i][k] * m.value(k,j));
                }
            }
        }
        return aux;
    }
    
    public Matrix mul(int n){
        Matrix aux = new Matrix(rows,cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                aux.setValue(i,j,this.matrix[i][j] * n);
            }
        }
        return aux;
    }
    
    public static Matrix Null(int r, int c){
        Matrix aux = new Matrix(r,c);
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                aux.setValue(i,j,0);
            }
        }
        return aux;
    }
    
    public static Matrix fill(int r, int c,int v){
        Matrix aux = new Matrix(r,c);
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                aux.setValue(i,j,v);
            }
        }
        return aux;
    }
    
    public static Matrix rand(int r, int c){
        Random rand = new Random();
        Matrix aux = new Matrix(r,c);
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                aux.setValue(i,j,rand.nextInt(101));
            }
        }
        return aux;
    }
    
    public static Matrix id(int r, int c){
        Matrix aux = new Matrix(r,c);
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(i == j){
                    aux.setValue(i,j,1);
                    continue;
                }
                aux.setValue(i,j,0);
            }
        }
        return aux;
    }
    
    public static Matrix seq(int from, int to){
       Matrix vect = new Matrix((to - from),1);
       int aux = from;
       for(int i = 0; i < (to - from); i++, aux++){
           vect.setValue(i, 0, aux);
       }
       return vect;
    }
    
    public static Matrix iseq(int from, int to){
       Matrix vect = new Matrix((from - to),1);
       int aux = from;
       for(int i = 0; i < (to - from); i++, aux--){
           vect.setValue(i, 0, aux);
       }
       return vect;
    }
    
    
}
