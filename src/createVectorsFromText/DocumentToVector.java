package createVectorsFromText;

import java.io.*;

import preProcessingDocumentsPipeline.*;

public class DocumentToVector
{

    private void recursivelyPreProcessFiles( File file ) throws IOException
    {

        if( file == null ) return;

        File[] list = file.listFiles();

        for( File f : list )
        {
            if( f!= null && f.isDirectory() )
            {
                recursivelyPreProcessFiles( f );
            }
            else 
            {
                if( f!=null )
                    StopWordsRemoval.removeAllStopWords( f );
            }
        }
    }

    private void recursivelyPreProcessFilesS( File file ) throws IOException
    {

        if( file == null ) return;

        File[] list = file.listFiles();

        for( File f : list )
        {
            if( f!= null && f.isDirectory() )
            {
                recursivelyPreProcessFilesS( f );
            }
            else 
            {
                if( f!=null )
                    Stemming.stemmingDriver( f );
            }
        }
    }

    public static void main( String[] args ) throws IOException
    {

        File inputRootDirectory = new File( "inputs" );
        new DocumentToVector().recursivelyPreProcessFiles( inputRootDirectory );
        File nextPipe = new File( "outputs" );
        new DocumentToVector().recursivelyPreProcessFilesS( nextPipe );

    }
}
