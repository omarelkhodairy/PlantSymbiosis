package com.example.symbio.ui.main;
import java.util.ArrayList;
import java.util.Iterator;


public class bfs {
    int start;
    int end;
    int vertices;

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }


    ArrayList<Boolean> vis= new ArrayList<Boolean>();
    ArrayList<ArrayList<Integer>> adj;
    ArrayList<Integer> pre = new ArrayList<Integer>();
    ArrayList<Integer> path= new ArrayList<Integer>();

    public ArrayList<Integer> getPath() {
        return path;
    }

    boolean checkNodeExist(int a, int b){
        if(a<adj.size() && b<adj.size())
        for(int i=0; i<adj.get(a).size(); i++){
            if(adj.get(a).get(i)== b)
                return true;
        }
        return false;
    }

    public void addEdge(int a, int b){
        if(!checkNodeExist(a,b)) {
            if(a<adj.size() && b<adj.size()) {
                adj.get(a).add(b);
                adj.get(b).add(a);
           }
        }
    }

    public bfs(int s, int e, int v){
        start = s;
        end = e;
        vertices =v;
        adj = new ArrayList<ArrayList<Integer>>(vertices+1);
        for (int i=0; i < vertices; i++)
            adj.add(new ArrayList<Integer>());
    }

    boolean doBFS(int s, int e) {
        vis.clear();
        pre.clear();
        for (int i = 0; i<vertices; i++){

            vis.add(false);
            pre.add(-1);
        }
        ArrayList<Integer> q = new ArrayList<Integer>();
        vis.set(s,true);
        q.add(s);


        while(!q.isEmpty()){
            int u =q.get(0);
            q.remove(0);

            int size=adj.get(u).size();

            for(int i =0; i <size; i++ ){
                if( !vis.get(adj.get(u).get(i))){
                    vis.set(adj.get(u).get(i),true);
                    pre.set(adj.get(u).get(i) , u );
                    q.add(adj.get(u).get(i));

                    if(adj.get(u).get(i)== e){
                        return true;
                    }

                    //System.out.println(pre);
                    //System.out.println(adj.get(u).get(i));


                }
            }
        }

        return false;
    }
    public void makePath(int s, int e){
        path= new ArrayList<Integer>();
        start=s;
        end=e;
        if(!doBFS(s,e))
            return;
        int crawl = e;
        path.add(crawl);
        while(pre.get(crawl) != -1){
            path.add(pre.get(crawl));
            System.out.println(crawl);
            crawl= pre.get(crawl);
        }



    }


}
