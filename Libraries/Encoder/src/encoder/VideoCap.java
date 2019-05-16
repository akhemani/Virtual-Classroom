/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encoder;

/**
 *
 * @author define
 */

import java.util.Vector;
import javax.media.*;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.CaptureDeviceInfo;
import javax.media.format.YUVFormat;
import javax.media.protocol.FileTypeDescriptor;

public class VideoCap{

    static VideoFormat videoFormat;
    static AudioFormat audioFormat;
    static CaptureDeviceInfo videoDevice;
    static CaptureDeviceInfo audioDevice;

    public static void main(String[] args) {
    try {
        Vector deviceList = CaptureDeviceManager.getDeviceList(new VideoFormat(VideoFormat.YUV)); //get all media devices
        

        CaptureDeviceInfo device = (CaptureDeviceInfo) deviceList.firstElement(); //in this computer the only capture device is in=built webcam stays at 0th position
        
        Format[] formats = device.getFormats(); //get all formats

        for (int x = 0; x < formats.length; x++) {
            if (formats[x] != null && formats[x] instanceof VideoFormat) {
                videoFormat = (VideoFormat) formats[x]; //take the video format
                videoDevice = device;
            }
            if (formats[x] != null && formats[x] instanceof AudioFormat) {
                audioFormat = (AudioFormat) formats[x]; //take the audio format
                //audioDevice = device;
            }
        }
        //create data sources
        DataSource videoDataSource = Manager.createDataSource(device.getLocator());

        deviceList = CaptureDeviceManager.getDeviceList(new AudioFormat(null)); //get all media devices
        device = (CaptureDeviceInfo) deviceList.firstElement();

        DataSource audioDataSource=Manager.createDataSource(device.getLocator());

        DataSource[] dArray=new DataSource[2];
        dArray[0]=videoDataSource;
        dArray[1]=audioDataSource;

            DataSource mixedDataSource = Manager.createMergingDataSource(dArray);

        //format for output

            Format[] outputFormats=new Format[2];
            outputFormats[0]=new VideoFormat(VideoFormat.YUV);
            outputFormats[1]=new AudioFormat(AudioFormat.LINEAR);
        //output type
            FileTypeDescriptor outputType=new FileTypeDescriptor(FileTypeDescriptor.MSVIDEO);

        //settingup Processor
            ProcessorModel processorModel=new ProcessorModel(mixedDataSource, outputFormats, outputType);
            Processor processor=Manager.createRealizedProcessor(processorModel);

        //settingup sink
            DataSource outputDataSource=processor.getDataOutput();
            MediaLocator destination=new MediaLocator("file:.\\testcam.avi");
            DataSink dataSink=Manager.createDataSink(outputDataSource, destination);
            dataSink.open();

            //start sink + processor
            Thread.sleep(4000);
            dataSink.start();
            processor.start();

            Thread.sleep(4000);

            dataSink.close();
            processor.stop();
            processor.close();


    } catch (Exception ex) {
        System.out.println(ex);
        ex.printStackTrace();

    }

}
}