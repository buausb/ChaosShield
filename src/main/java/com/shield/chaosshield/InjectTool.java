package com.shield.chaosshield;


import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class InjectTool {
//
//    static int pidNum;
//
//    public static void inject(Chaos chaos) throws Exception {
//        ProcessBuilder processBuilder = new ProcessBuilder("chmod", "+x", chaos.getStartPath());
//        Process start = processBuilder.start();
//        start.waitFor();
//
//        ProcessBuilder builder = new ProcessBuilder(chaos.getInjectCommands(new ArrayList<>()));
//        Process process = builder.start();
//        Field pid = process.getClass().getDeclaredField("pid");
//        pid.setAccessible(true);
//        pidNum = pid.getInt(process);
//        System.out.println("===================> " + pidNum);
//    }
//
//    public static void kill(Chaos chaos) throws Exception {
//        ProcessBuilder processBuilder = new ProcessBuilder("chmod", "+x", chaos.getKillerPath());
//        Process start = processBuilder.start();
//        start.waitFor();
//
////        List<String> attrs = new ArrayList<>();
////        attrs.add(Integer.toString(pidNum));
//        Scanner s = new Scanner(System.in);
//        int num = s.nextInt();
//
//        ProcessBuilder builder = new ProcessBuilder(chaos.getKillerPath(), Integer.toString(num));
//        builder.start();
//    }
//
//    public static void main(String[] args) throws Exception {
//        System.out.println("Java工作目录：" + System.getProperty("user.dir"));
//        System.out.println("JAVA_HOME=" + System.getenv("JAVA_HOME"));
////        machine chaos
////        boolean flag = true;
////        Scanner s = new Scanner(System.in);
////        CpuHigh chaos = new CpuHigh();
////        while (flag) {
////            int i = s.nextInt();
////            if (i == 1) {
////                inject(chaos);
////            } else if (i == 2) {
////                kill(chaos);
////            } else {
////                flag = false;
////            }
////        }
//
////        load JVM_Sandbox
//
////        Scanner s = new Scanner(System.in);
////        int pid = s.nextInt();
////        ProcessBuilder pb = new ProcessBuilder("./sandbox.sh","-p",Integer.toString(pid));
////        pb.directory(new File("./sandbox/bin"));
////
////        Map<String, String> env = pb.environment();
////        env.put("JAVA_HOME", "/usr/lib/jvm/java-8-openjdk-amd64"); // 替换为实际 JDK 路径
//////        ProcessBuilder pb = new ProcessBuilder("jps");
////        Process start = pb.start();
//////        new OutputStreamWriter(start.getOutputStream())
////        InputStream inputStream = start.getInputStream();
////        StringBuilder sb = new StringBuilder();
////        byte[] buffer = new byte[1024];
////        int bytesRead;
////        while ((bytesRead = inputStream.read(buffer)) != -1) {
////            String data = new String(buffer, 0, bytesRead);
////            sb.append(data);
////        }
////        int num = start.waitFor();
////        System.out.println(num + " => "+sb+" <=");
//
//        System.out.println("pid");
//        Scanner s = new Scanner(System.in);
//        int pid = s.nextInt();
//        ProcessBuilder pb = new ProcessBuilder("sh","-c","echo $JAVA_HOME");
//        pb.directory(new File("./sandbox/bin"));
//        pb.environment().put("JAVA_HOME", "/usr/lib/jvm/java-8-openjdk-amd64");
//
//        System.out.println("Load ?");
//        s.nextInt();
//
//        Process process = pb.command("./sandbox.sh", "-p", Integer.toString(pid)).start();
//        BufferedReader retReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String ret;
//        while ((ret = retReader.readLine()) != null) {
//            System.out.println(ret);
//        }
//        int loadExit = process.waitFor();
//        System.out.println("load =>" + loadExit);
//
//        System.out.println("change ?");
//        s.nextInt();
//
//        process = pb.command("./sandbox.sh","-p",Integer.toString(pid),"-l").start();
//        retReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        ret = null;
//        while ((ret = retReader.readLine()) != null) {
//            System.out.println(ret);
//        }
//        int exit = process.waitFor();
//        System.out.println("check module =>" + exit);
//
//        System.out.println("do change ?");
//        s.nextInt();
//
//        process = pb.command("./sandbox.sh","-p",Integer.toString(pid),"-d", "change-method-ret-val-moudle/change?class=com.shield.App&method=getStr&ret_class=java.lang.String&ret_val=2").start();
//        retReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        ret = null;
//        while ((ret = retReader.readLine()) != null) {
//            System.out.println("do change +>" + ret);
//        }
//        exit = process.waitFor();
//        System.out.println("check module =>" + exit);
//
//        System.out.println("shut down ?");
//        s.nextInt();
//
//        process = pb.command("./sandbox.sh","-p",Integer.toString(pid),"-S").start();
//        retReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        ret = null;
//        while ((ret = retReader.readLine()) != null) {
//            System.out.println("shut down +>" + ret);
//        }
//        exit = process.waitFor();
//        System.out.println("shut down =>" + exit);
//    }

}
