

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


/*
Input file (csv) =

    10, 20, 30
    40, 50, 60
    70, 80, 90

Output (transpose) =

    10, 40, 70
    20, 50, 80
    30, 60, 90
*/

public class MatrixTranspose {

    /*
    map function purpose =

    map(key = 0, value = "10, 20, 30") => {0:{0,"10"}},  {1:{0,"20"}},  {2:{0,"30"}}
    map(key = 1, value = "40, 50, 60") => {0:{1,"40"}},  {1:{1,"50"}},  {2:{1,"60"}}
    map(key = 2, value = "70, 80, 90") => {0:{2,"70"}},  {1:{2,"80"}},  {2:{2,"90"}}
    */

    public static class Map extends Mapper<LongWritable, Text, LongWritable, MapWritable> {

        public void map(LongWritable key, Text inputFile, Context context) throws IOException, InterruptedException {

            StringTokenizer rdd = new StringTokenizer(inputFile.toString(), ",");
            MapWritable mw = new MapWritable();
            int col_id = 0;
            while (rdd.hasMoreElements()) {
                mw.put(key, new Text((String) rdd.nextElement()));
                context.write(new LongWritable(col_id), mw);
                col_id++;
            }
        }
    }

    /*
    reduce function purpose =

    reduce({0: [{0,"10"},{1,"40"},{2,"70"}]})  =>  {0: "10, 40, 70"}
    reduce({1: [{0,"20"},{1,"50"},{2,"80"}]})  =>  {1: "20, 50, 80"}
    reduce({2: [{0,"30"},{1,"60"},{2,"90"}]})  =>  {2: "30, 60, 90"}
    */

    public static class Reduce extends Reducer<LongWritable, MapWritable, LongWritable, Text> {

        public void reduce(LongWritable key, Iterable<MapWritable> maps, Context context) throws IOException, InterruptedException {

            SortedMap<LongWritable, Text> rdds = new TreeMap<>();
            for (MapWritable mw : maps) {
                for(Entry<Writable, Writable>  entry : mw.entrySet()) {
                    rdds.put((LongWritable) entry.getKey(), (Text) entry.getValue());
                }
            }

            StringBuffer buffer = new StringBuffer();
            for(Text rdd : rdds.values()) {
                buffer.append(rdd.toString());
                buffer.append(",");
            }
            context.write(key, new Text(buffer.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = new Job(conf, "MatrixTranspose");
        job.setJarByClass(MatrixTranspose.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(MapWritable.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }

}
