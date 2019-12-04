package com.zhu.guava.precondition;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

//前置条件
public class PreConditionTest {
    private String label;
    private int[] value = new int[5];
    private int currentIndex;

    public PreConditionTest(String lable){
        this.label = Preconditions.checkNotNull(lable,"label 不能为null");
    }

    public void updateCurrentIndexValue(int index,int value2Set){
        this.currentIndex = Preconditions.checkElementIndex(index,this.value.length,"index out of bounds for value");
        Preconditions.checkArgument(value2Set<100,"value can not more than 100");
        value[this.currentIndex] = value2Set;
    }
    public void doOperation(){
        Preconditions.checkState("open".equalsIgnoreCase(label)&&value[currentIndex]==10,"不能执行操作");
    }


}
