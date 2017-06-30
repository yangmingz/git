package com.jxust.ssm.controller;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {

    /**
	 * 这里可以自定义一个ApiResult类作为返回值的载体，这个类里面需要用到另外一个类，但是又不能确定要用到的那个类的具体类型，所以暂时用T来代替，当具体的程序知道要用到的那个类型是什么的时候就用那个类型来代替T
	 */
	private static final long serialVersionUID = 1L;
	public int status;        //返回状态，0标示正确返回，-1表示错误返回，
	public String errmsg="err msg 404";    //返回的错误信息
	public T data;            //返回的数据集合,上边说的用到的那个类
    /**
    * get,set方法，相关的静态方法
    */
}