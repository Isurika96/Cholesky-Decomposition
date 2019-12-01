/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choleskynew;

/**
 *
 * @author Isurika
 */
import java.util.Scanner;
public class Choleskynew {

    
public static void main(String[] args) {
       Scanner scn = new Scanner(System.in);
       System.out.println("Enter the number of rows : ");
       int nrows = scn.nextInt();
       System.out.println("Enter the number of columns : ");
       int ncolums = scn.nextInt();
       int matrix[][]= new int[nrows][ncolums];
       System.out.println("Enter the elements : ");
       for(int i =0; i<nrows; i++)
       {
           for(int j =0;  j < ncolums; j++)
           {
               matrix[i][j] = scn.nextInt();
           }
       }
int B[]=new int[nrows];
System.out.println("Enter elements of the [B]:");
for(int i=0;i<nrows;i++){
B[i]=scn.nextInt();
}

System.out.println("Matrix A:");

for(int i=0;i<nrows;i++){
    for(int j=0;j<ncolums;j++){
    
System.out.print(matrix[i][j]+"  ");    
    }
    
System.out.println();
}
System.out.println("Matrix [B] :");
for(int i=0;i<nrows;i++){
System.out.print(B[i]+"  ");
System.out.println();
}
       //checking the input matrix is Square Matrix
if(nrows != ncolums)
{
System.out.println("The given matrix is not a square matrix. So, it cannot use cholesky method.");
}
else
{
   boolean symmetric= true;
   boolean positivedef = true;
    double posdef=matrix[0][0];

   for(int i=0; i< nrows; i++){
   for (int j=0; j < ncolums; j++){
if(matrix[i][j] != matrix[j][i])
{
symmetric = false;
break;
}
if(matrix[i][j]>posdef){
    posdef=matrix[i][j];
    if(i!=j)
       positivedef=false;
    
    else
        positivedef=true;
}
   }
   }
   
 if(symmetric==false){
    System.out.println("Matrix A is not symmetric.Therefore we cannot use cholesky method.");
}
if(positivedef==false){
    System.out.println("Matrix A is not positive definite.Therefore we cannot use cholesky method.");
    
}if(symmetric & positivedef){
    System.out.println("This is a symmetric matrix and positive definite.Then we can use cholesky method.");
     System.out.println("[A][X]=[B]");
    double lowerTran[][]=new double[nrows][ncolums];
    
    lowerTran[0][0]=Math.sqrt(matrix[0][0]);
    
    for(int i=0;i<nrows;i++){
    for(int j=0;j<ncolums;j++){
    if(i>0 & j==0){
        lowerTran[i][0]=(matrix[i][0]/lowerTran[0][0]);
    }
     if(i>0 & j>0 & i==j){
        double sum1=0;
        for(int s=0;s<=(j-1);s++){
       sum1=sum1+(lowerTran[j][s]*lowerTran[j][s]);
        }    
    lowerTran[i][i]=Math.sqrt(matrix[i][i]-sum1);
    }
    
    if(i>j & j>0){
        double sum2=0;
        for(int s=0;s<=(j-1);s++){
            sum2+=(lowerTran[j][s]*lowerTran[i][s]);
        }
        lowerTran[i][j]=(matrix[i][j]-sum2)/lowerTran[j][j];
    }
    if(i<j & j>0){
        lowerTran[i][j]=0;
    }
    }
    }
    System.out.println();
    
   System.out.println("Lower Triangular matrix: ");
   for(int i=0;i<nrows;i++){
    for(int j=0;j<ncolums;j++){
        System.out.print(lowerTran[i][j]+ " ");
    }
     System.out.println();
    }
   
   double upperTran[][]=new double[nrows][ncolums];
    for(int i=0;i<nrows;i++){
    for(int j=0;j<ncolums;j++){
      upperTran[i][j]=lowerTran[j][i];
    }
    }
    
    System.out.println();
    System.out.println("Upper Triangular matrix : ");
    for(int i=0;i<nrows;i++){
    for(int j=0;j<ncolums;j++){
        System.out.print(upperTran[i][j]+" ");
    }
     System.out.println();
    }
   System.out.println();

   double Y[]=new double[nrows];
   for(int i=0;i<nrows;i++){
       double sum3=0;
       for(int j=0;j<ncolums;j++){
           sum3+=(lowerTran[i][j]*Y[j]);
                  
                 
       }
       sum3=sum3-(lowerTran[i][i]*Y[i]);

         Y[i]=(B[i]-sum3)/lowerTran[i][i];

   }
   System.out.println("[LowerTrangular][Y]=[B]");
   
   System.out.println("Solutions of Y: ");
   for(int i=0;i<Y.length;i++){
   System.out.print(Y[i]+"   "); 
}
   System.out.println();

   double X[]=new double[nrows];
   for(int i=(nrows-1);i>=0;i--){
       double sum=0;
       for(int j=0;j<ncolums;j++){
           sum+=(upperTran[i][j]*X[j]);
           
             }
       
       sum=sum-(upperTran[i][i]*X[i]);
          X[i]=(Y[i]-sum)/upperTran[i][i];

   }
   System.out.println("[UpperTrangular][X]=[Y]");
   System.out.println("Solutions of X: ");
   for(int i=0;i<X.length;i++){
   System.out.print(X[i]+" "); 
}
   
   
}


        }
 
   
    }
    
}
