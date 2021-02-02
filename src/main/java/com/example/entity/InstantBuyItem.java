package com.example.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class InstantBuyItem extends Model<InstantBuyItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    private String name;

    private Integer number;

    private BigDecimal initialPrice;

    private BigDecimal buyPrice;

    private String sellPoint;

    private LocalDateTime createTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    @Override
    protected Serializable pkVal() {
        return this.itemId;
    }

}
