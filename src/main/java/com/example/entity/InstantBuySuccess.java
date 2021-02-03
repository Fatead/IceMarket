package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class InstantBuySuccess extends Model<InstantBuySuccess> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "success_id", type = IdType.AUTO)
    private Integer successId;

    private Integer itemId;

    private String userName;

    private Integer state;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.successId;
    }

}
