package cn.angelbell.oa.util;

import java.util.List;

public class Page {
	//已知数据
    private int pageNum;    //当前页,从请求那边传过来。
    private int pageSize;    //每页显示的数据条数。
    private int totalRecord;    //总的记录条数。查询数据库得到的数据
    
    //需要计算得来
    private int totalPage;    //总页数，通过totalRecord和pageSize计算可以得来
    //开始索引，也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize，
    //就知道了limit语句的两个数据，就能获得每页需要显示的数据了
    private int startIndex;        
        
    
    //将每页要显示的数据放在list集合中
    public List<?> list;
    
    //分页显示的页数,比如在页面上显示1，2，3，4，5页，start就为1，end就为5，这个也是算过来的
    private int start;
    private int end;
    
    private int nextPage;
    private int lastPage;
    
    private String pageContent;   //分页内容
   
    
    //通过pageNum，pageSize，totalRecord计算得来tatalPage和startIndex
    //构造方法中将pageNum，pageSize，totalRecord获得
    public Page(int pageNum,int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
//get、set方法。
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;

        
        //totalPage 总页数
        if(totalRecord%pageSize==0){
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            this.totalPage = totalRecord / pageSize;
        }else{
            //不整除，就要在加一页，来显示多余的数据。
            this.totalPage = totalRecord / pageSize +1;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public String getPageContent() {
		StringBuilder pages = new StringBuilder();
		
		if(totalRecord>0&&this.totalPage>1){
			
			
			if(pageNum<=1){
				pages.append("<span><a href='javascript:void(0)'>首页</a></span>");
				pages.append("<span><a href='javascript:void(0)'>上一页</a></span>");
			}else{
				this.lastPage = pageNum-1; //上一页
				pages.append("<span><a href='javascript:goPage(1)'>首页</a></span>");
				pages.append("<span><a href='javascript:goPage("+lastPage+")'>上一页</a></span>");
			}
			
			//开始索引
	        this.startIndex = (pageNum-1)*pageSize ;
	        //显示5页，这里自己可以设置，想显示几页就自己通过下面算法修改
	        this.start = 1;
	        this.end = 5;
	        //显示页数的算法
	        if(this.pageNum>=this.totalPage){
	        	 this.nextPage = pageNum; 
	        }else{
	        	 this.nextPage = pageNum+1;  //下一页
	        }
	        
	       
	      

	        if(totalPage <=5){
	            //总页数都小于5，那么end就为总页数的值了。
	            this.end = this.totalPage;
	        }else{
	            //总页数大于5，那么就要根据当前是第几页，来判断start和end为多少了，
	            this.start = pageNum - 2;
	            this.end = pageNum + 2;
	            
	            if(start <=0){
	                //比如当前页是第1页，或者第2页，那么就不如和这个规则，
	                this.start = 1;
	                this.end = 6;
	            }
	            if(end > this.totalPage){
	                //比如当前页是倒数第2页或者最后一页，也同样不符合上面这个规则
	                this.end = totalPage;
	                this.start = end - 5;
	            }
	        }
	        
	        for(int i=start;i<=end;i++){
	        	 if(i==pageNum){
	        		 pages.append("<span><a href='javascript:void(0)' class='dq'>"+i+"</a></span>");
	        	 }else{
	        		 pages.append("<span><a href='javascript:goPage("+i+")'>"+i+"</a></span>");
	        	 }
	        }
	        
			
			if(pageNum>=totalPage){
				pages.append("<span><a href='javascript:void(0)'>下一页</a></span>");
				pages.append("<span><a href='javascript:void(0)'>尾页</a></span>");
			}else{
				pages.append("<span><a href='javascript:goPage("+nextPage+")'>下一页</a></span>");
				pages.append("<span><a href='javascript:goPage("+totalPage+")'>尾页</a></span>");
			}
			
		}
		this.pageContent = pages.toString();
		return this.pageContent;
	}
	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	
	
    
}
