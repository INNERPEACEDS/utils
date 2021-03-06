package com.wgb.utils.dao.oracle;

import com.wgb.utils.entity.oracle.Image;
import com.wgb.utils.entity.oracle.ImageExample;
import java.util.List;

import com.wgb.utils.entity.oracle.dto.ImageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int countByExample(ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int deleteByExample(ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int insert(Image record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int insertSelective(Image record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    List<Image> selectByExample(ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    Image selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Image record, @Param("example") ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Image record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Image record);

    /**
     * 查询上传数据信息
     * @param imageDTO
     * @return
     */
    List<Image> listImageByDTO(@Param("imageDTO") ImageDTO imageDTO);
}