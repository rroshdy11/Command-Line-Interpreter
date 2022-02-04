
//Reda Roshdy Awd Mohamed 20196022
//Mariam mahmoud Atitto 20196070
//Ziad Mohamed AbdelHamed 20196024
package terminal;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.stream.Stream;
//////////////////////////parser class
class Parser {
    
    String commandName;
    String [] args;
    
    //it checks if the command exists or not and checks on the number of the given args
    public boolean parse(String input)
    {
       String[] cmd_args;
       cmd_args =input.split(" ");
       commandName=cmd_args[0];
       
            if(cmd_args.length>1&&!(cmd_args[1].equals("-r")))
            {
                // if the command doesnot contani -r 
               args=new String[cmd_args.length-1];
               for(int i=1;i<cmd_args.length;i++)
               {   
                   args[i-1]=cmd_args[i];
               }
            }
            else if(cmd_args.length>2&&(cmd_args[1].equals("-r")))
            {
                //if the command contains -r and takes args like (cp -r src dest)
                commandName=commandName+" "+cmd_args[1];
                args=new String[cmd_args.length-2];
               for(int i=2;i<cmd_args.length;i++)
               {   
                   args[i-2]=cmd_args[i];
               }
                
            }
             else if(cmd_args.length>1&&(cmd_args[1].equals("-r")))
            {
                //if the command contains -r and takes no args like ls -r
                commandName=commandName+" "+cmd_args[1];
                
            }
            
       
       if(commandName.equals("pwd"))
       {
           if(args!=null)
           {
               return false;
           }
       }
       else if (commandName.equals("exit"))
       {
           if(args!=null)
           {
               return false;
           }
       }
        else if (commandName.equals("echo"))
       {
           if(args==null)
           {
               return false;
           }
       }
       else if (commandName.equals("ls"))
       {
           if(args!=null)
           {
               return false;
           }
       }
       else if (commandName.equals("cp"))
       {
    	   if(args==null)
    	   {
    		   return false;
    	   }
           else if(args.length<2)
    	   {
    		   return false;
    	   }
    	   else if(args.length>2)
    	   {
    		   return false;
    	   }
       }
        else if (commandName.equals("ls -r"))
       {
    	   if(args!=null)
    	   {
    		   return false;
    	   }
       }
        else if (commandName.equals("mkdir"))
       {
    	   if(args==null)
    	   {
    		   return false;
    	   }
    	   else if(args.length>2)
    	   {
    		   return false;
    	   }
       }
       else if (commandName.equals("touch"))
       {
           if(args==null)
           {
               return false;
           }
           else if(args.length>1)
           {
               return false;
           }
       }
          else if (commandName.equals("cat"))
       {
           if(args==null)
           {
               return false;
           }
           else if(args.length>2)
           {
               return false;
           }
       }
          else if (commandName.equals("rm"))
       {
           if(args==null)
           {
               return false;
           }
           else if(args.length>1)
           {
               return false;
           }
       }
          else if (commandName.equals("echo"))
       {
           if(args==null)
           {
               return false;
           }
       }
         else if (commandName.equals("cd"))
       {
           if(args==null)
           {
               return true;
           }
           if(args.length>1)
           {
               return false;
           }
       }
         else if (commandName.equals("rmdir"))
       {
           if(args==null)
           {
               return false;
           }
           else if(args.length>1)
           {
               return false;
           }
       }
       else if (commandName.equals("cp -r"))
       {
           if(args==null)
           {
               return false;
           }
           else if(args.length>2)
           {
               return false;
           }
       }
       else 
       {
           return false;
       }
        
        
        
       
        return true;
    }
    
   //Retruns the Command Name
    public String getCommandName(){
        return commandName;
        
    }
    //Returns the Args 
    public String[] getArgs()
    {
        return args;
    }
    
}

public class Terminal{

   private static Parser p1;
   /////////////////////////
   public static File defualtdir,currentdir;
   static Terminal t1=new Terminal();
   static int counter=1;
  static String []prevpaths=new String [100];
   
   
   ///////////////////////////////////////////////////touch 
   public void touch(String[] args) throws IOException
    { 
      
     String path=args[0];
     File f;
     if(path.contains("\\"))
     {
          f=new File(path);
     }
             else{
           f=new File(currentdir+"\\"+path);
        }
     try{
         f.createNewFile();
     }
      catch(IOException e)
        {
           System.out.println("Invalid Path");
        }
        
    }
   public void cd(String newpath)
   {
       
       
       if(newpath.equals(".."))
       {


           if(counter!=0){
                 counter--;
           }
           currentdir=new File(prevpaths[counter]);
          
       }
       else
       {
           
           if(!newpath.contains("\\"))
           {
               newpath=currentdir+"\\"+newpath;
           }
           File newdir=new File(newpath);
           if(newdir.isDirectory())
           {
                prevpaths[counter]=currentdir.getPath();
                currentdir=newdir;
           }
           else
           {
               System.out.println(newdir.getPath()+":No such A Directory");
           }
           counter++;
       }
   }
   // changing the directory to the default
   public void cd()
   {
       prevpaths[counter]=currentdir.getPath();
       currentdir=new File(defualtdir.getPath());
       counter++;
   }
    //Printing the file Contents 
    public void cat(String[] args) throws FileNotFoundException 
    {
        for(int i=0;i<args.length;i++)
        {
    try {
          String path=args[i];
          File f;
          if(path.contains("\\"))
     {
          f=new File(path);
     }
             else{
           f=new File(currentdir+"\\"+path);
        }
        try (Scanner myReader = new Scanner(f)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            } }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
        }
    }
    
    private void rm(String[] args)
     {
          String path=args[0];
          File f;
          if(path.contains("\\"))
        {
            f=new File(path);
        }
             else{
           f=new File(currentdir+"\\"+path);
        }
          if(f.isFile())
          {
         f.delete();
          }
          else
          {
              System.out.println(f.getPath()+": No Such A file");
          }
    }
    public  String pwd()
    {
        if(defualtdir.getPath().equals(currentdir.getPath()))
        {
            return (defualtdir.getAbsolutePath());
        }
        else
        {
            return (currentdir.getAbsolutePath());
        
        }
    }
    ///////////////////////////////////////////////////////////cp -r 
    public  void cp_r(String src ,String dest) throws IOException
    {
        if(!src.contains("\\"))
        {
            src=currentdir+"\\"+src;
        }
        if(!dest.contains("\\"))
        {
            dest=currentdir+"\\"+dest;
        }
        File srcfile=new File (src);
        File destFile=new File(dest+"\\"+srcfile.getName());
        t1.mkdir(destFile.getPath());
         
        String files[] = srcfile.list();

        for (String file : files)
        {
            File srcFile = new File(srcfile, file);
            File destf = new File(destFile, file);
            if(srcFile.isFile())
            {
            cp(srcFile.getPath(), destf.getPath());
            }
            else
            {
                
                mkdir(destFile.getPath()+"\\"+srcFile.getName());
            }
            
        }
        
    }
    
    ////////////////////////////////////////////////////ls -r
    public void ls_r(File c) throws IOException 
    {
        Files.walk(currentdir.toPath()).forEach(path -> System.out.println(path.getFileName()));
    }
    ////////////////////////////////////////////////////////rmdir
    public void rmdir(String filePath) throws IOException {
        if(filePath.equals("*"))
        {
                Files.list(new File(currentdir.getPath()).toPath())
                .limit(20)
                .forEach(path -> {
                    if(path.toFile().isDirectory()){
                    path.toFile().delete();
                    }
                });
        }
        else
        {
            String path=filePath;
            if(!(filePath.contains("\\")))
            {
                path=currentdir+"\\"+filePath;
                
            }
            File del=new File(path);
             boolean flag=true;
            if(del.isDirectory()){
              flag=del.delete();
            }
            else
            {
                System.out.println(del.getPath()+": No Such a File");
            }
            if(!flag)
            {
                 System.out.println(del.getName()+": Is Not Empty");
            }
        }
    }
    ////////////////////////////////////////////////////////////copy file to file
    public void cp(String Source,String Destination) throws FileNotFoundException, IOException   
    {
    	String Path=Source;
    	if(Source.contains("\\"))
    	{
    		if(Destination.contains("\\"))
    		{
    			
    		}
    		else
    		{
    			Destination=currentdir+"\\"+Destination;
    		}
    	}
    	else 
    	{
    		Source=currentdir+"\\"+Path;
    		if(Destination.contains("\\"))
    		{
    			
    		}
    		else
    		{
    			Destination=currentdir+"\\"+Destination;
    		}
   
    	}
    	File orignial = new File(Source);
        if(Source.contains(".txt")&&orignial.isFile()){
        InputStream is ;
        OutputStream os ;
        is = new FileInputStream(Source);
        os = new FileOutputStream(Destination);
    try {
        
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
    	
    }
        else 
        {
            System.out.println(Source+" There is no such a file");
        }
    }
    
    public  void exit()
    {
        System.exit(0);
    }
    public  boolean mkdir(String args)
    {
        String path=args;
        File newfile;
        if(path.contains("\\"))
        {
            newfile=new File(path);
             
        }
        else{
            newfile=new File(currentdir+"\\"+path);
        } 
        return newfile.mkdir();
        
    }
    public void ls() throws IOException
    {
        Files.list(new File(currentdir.getPath()).toPath())
                .limit(20)
                .forEach(path -> {
                    System.out.println(path.getFileName());
                });
    }
    public String echo(String args[]) 
    {
        String echostr="";
           for(String arg : args)
           {
            echostr=echostr+" "+arg;

           }
           return echostr;
    }
    
    
    public static void main(String[] args) throws IOException {
        
        //Intializing the paths and put the deafualt path in the first element of the array
        defualtdir=new File(".");
        currentdir=new File(".");
         prevpaths[0]=defualtdir.getPath();
        do{
        p1=new Parser();
        System.out.print(">");
        
        //Taking the command from the user
        Scanner input=new Scanner(System.in);
        String cmd=input.nextLine();
        //if the parse returns false thats mean there is an error in the command name or the number of parameters
        if(!(p1.parse(cmd)))
        {
            System.out.println("Erorr Invalid Command OR check the parmeters number ");
            continue;
        }
        t1.chooseCommandAction();
        }while(true);
        
    }

    public  void chooseCommandAction() throws IOException{
        
        //in this function we will call the command function and print the output of it if it has return
        
        if(p1.getCommandName().equals("pwd"))
        {
            System.out.println(t1.pwd());
        }
        else if(p1.getCommandName().equals("exit"))
        {
            t1.exit();
        }
        else if(p1.getCommandName().equals("mkdir"))
        {
            String []args=p1.getArgs();
           boolean m= t1.mkdir(args[0]);
           if(!m)
           {
               System.out.println("invalid path Entered");
           }
        }
        else if(p1.getCommandName().equals("cp"))
        {
        	String [] args=p1.getArgs();
        	t1.cp(args[0], args[1]);
        }
        else if(p1.getCommandName().equals("ls"))
        {
        
        	t1.ls();
        }
        else if(p1.getCommandName().equals("ls -r"))
        {
        
        	t1.ls_r(currentdir);
        }
        else if(p1.getCommandName().equals("rmdir"))
        {
        
        	String [] args=p1.getArgs();
        	t1.rmdir(args[0]);
        }
        else if(p1.getCommandName().equals("touch"))
        {
           t1.touch(p1.getArgs());
        }
         else if(p1.getCommandName().equals("cat"))
        {
           t1.cat(p1.getArgs());
        }
        else if(p1.getCommandName().equals("rm"))
        {
           t1.rm(p1.getArgs());
        }
        else if(p1.getCommandName().equals("echo"))
        {
           System.out.println(t1.echo(p1.getArgs()));
        }
        else if(p1.getCommandName().equals("cd"))
        {  if(p1.getArgs()==null)
            {
                t1.cd();
            }
            else
            {
                String[]  args=p1.getArgs();
                t1.cd(args[0]);
            }
        
        }
        else if(p1.getCommandName().equals("cp -r"))
        {  
                String[]  args=p1.getArgs();
                t1.cp_r(args[0],args[1]);
        }
    }
}
