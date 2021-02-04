package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = 1L;



    private String userName;

    private String password;

    private String mail;

    private String nickName;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
