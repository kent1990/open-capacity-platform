package com.open.capacity.entity;

/**
 * @create 2019年4月2日
 * Content :快递单号实体
 */

public class DeliverPost {

  
    private String orderId; 
    
   
    private String postId ;
    
    private int isArrived = 0 ;
    
     

	  
    
	public int getIsArrived() {
		return isArrived;
	}

	public void setIsArrived(int isArrived) {
		this.isArrived = isArrived;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
    
    


}
