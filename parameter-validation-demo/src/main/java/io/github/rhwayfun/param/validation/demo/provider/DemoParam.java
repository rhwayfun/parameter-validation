package io.github.rhwayfun.param.validation.demo.provider;

import io.github.rhwayfun.param.validation.common.annotation.MaxSize;
import io.github.rhwayfun.param.validation.common.annotation.NonValidation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rhwayfun on 2018/8/3.
 */
public class DemoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cityId;
    private Long ordrId;
    @NonValidation
    private String group;
    @MaxSize(value = 1, msg = "Size is greater than 1!")
    private List<Long> orderIds;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(Long ordrId) {
        this.ordrId = ordrId;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    @Override
    public String toString() {
        return "DemoParam{" +
                "cityId=" + cityId +
                ", ordrId=" + ordrId +
                ", group=\'" + group + "\'" +
                ", orderIds=" + orderIds +
                '}';
    }

}
