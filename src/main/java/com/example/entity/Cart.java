package com.example.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author vicente
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class Cart extends Model<Cart> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    private Integer userId;

    private Integer productId;

    private String productImage;

    private String productName;

    private BigDecimal productPrice;

    private Integer produceNum;


    @Override
    protected Serializable pkVal() {
        return this.cartId;
    }

}
