package com.tan.matrix;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileIOUtil {


    public static void move(String sourcePath, String targetDir) {
        Path sourceFile = Paths.get(sourcePath);
        String aa = sourceFile.getFileName().toString();
        Path targetFile = Paths.get(targetDir,aa);
        try {
            Files.move(sourceFile, targetFile, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String path, String charset) throws IOException {
        InputStream is = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        String line = null;
        try {
            is = new FileInputStream(path);
            ir = new InputStreamReader(is, charset);
            br = new BufferedReader(ir, 5000);
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals("")) {
                    sb.append(line).append("\n");
                }
            }
            return sb.toString();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static String read(String path) throws IOException {
        return read(path, "gbk");
    }


    public static void save(String path, String content) {
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(path);
            pr.print(content);
            pr.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

    }

    public static BufferedReader getReader(String file, String charset) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), charset));
            return reader;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
                reader = null;
            } catch (IOException e) {
            }
        }
    }

    public synchronized static void save(String path, String content, boolean isAppend) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path, isAppend);
            writer.write(content);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }

    }

    public synchronized static void save(String path, String content, String charset) {
        OutputStreamWriter writer = null;
        try {
            FileOutputStream fos = new FileOutputStream(path);
            writer = new OutputStreamWriter(fos, charset);
            writer.write(content);
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }

    }


    public synchronized static void save(String path, String content, String charset, boolean isAppend) {
        OutputStreamWriter writer = null;
        try {
            FileOutputStream fos = new FileOutputStream(path, isAppend);
            writer = new OutputStreamWriter(fos, charset);
            writer.write(content);
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }

    }

    public static void writeObj(String fileName, Object src) throws Exception {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(src);

        BufferedOutputStream bos = new BufferedOutputStream(out);
        bos.flush();
        bos.close();

    }

    public static Object ReadObj(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream in = new ObjectInputStream(bis);
        Object o = in.readObject();
        bis.close();
        return o;
    }

	
	/*public static ArrayList<Entry<String, Integer>> sortByValue(
            Map<String, Integer> keyfreqs) {

		ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
				keyfreqs.entrySet());

		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});

		return list;
	}*/

    public static ArrayList<Entry<String, Double>> sortByValueDouble(
            Map<String, Double> keyfreqs) {

        ArrayList<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(
                keyfreqs.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Double>>() {
            public int compare(Entry<String, Double> o1,
                               Entry<String, Double> o2) {
                double v2 = o2.getValue();
                double v1 = o1.getValue();

                if (v2 > v1) {
                    return 1;
                } else if (v2 < v1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        return list;
    }


    public static <PK extends Serializable, N extends Number> List<Entry<PK, N>> sortByValue(Map<PK, N> keyMap) {
        List<Entry<PK, N>> list = new ArrayList<Entry<PK, N>>(keyMap.entrySet());
        Collections.sort(list, new Comparator<Entry<PK, N>>() {
            @Override
            public int compare(Entry<PK, N> o1, Entry<PK, N> o2) {
                N v1 = o1.getValue();
                N v2 = o2.getValue();

                if (v2.doubleValue() > v1.doubleValue()) {
                    return 1;
                } else if (v2.doubleValue() < v1.doubleValue()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        return list;
    }

    public static void main(String[] args) {
        move("/tmp/a/q","/tmp/b");
    }

}
