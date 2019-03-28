package com.wgb.utils.controller.page.query;

import com.github.pagehelper.PageInfo;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.add.AddService;
import com.wgb.utils.service.query.page.PageService;
import com.wgb.utils.util.binding.result.BindingResultUtil;
import com.wgb.utils.util.constants.controller.ControllerConstant;
import com.wgb.utils.util.constants.forward.ForwardConstant;
import com.wgb.utils.util.constants.request.RequestMappingConstant;
import com.wgb.utils.util.constants.result.Constant;
import com.wgb.utils.util.regular.RegularUtil;
import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

/**
 * @author : innerpeace
 * @date : 2018/12/11 15:59
 */
@Slf4j
@Controller
public class BookRecordController {
    /**
     * 名称
     */
    private static String CONTROLLER_NAME = ControllerConstant.BOOK_RECORD;

    /**
     * 网页请求url
     */
    private static String htmlRequestUrl = ForwardConstant.FORWARD_BOOK_RECORD;

    @Autowired
    PageService pageService;
    @Autowired
    AddService addService;

    /**
     * 查询书籍
     * @param bookRecordDTO
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/downloadAndUpload/queryBookRecord")
    public @ResponseBody Result queryBookRecord(@Valid BookRecordDTO bookRecordDTO, BindingResult bindingResult, Model model) {
        log.debug("绑定参数：{}", bindingResult);
        // 参数校验
        if (bindingResult.hasErrors()) {
            return BindingResultUtil.bindingResultHasError(bindingResult, model);
        }
        log.info("开始查询{}：", CONTROLLER_NAME);
        log.info("查询参数信息：[编号：{}；名称：{}；备注：{}；日期范围：({}-{}){}-{}]", bookRecordDTO.getId(), bookRecordDTO.getName(), bookRecordDTO.getRemarks(), bookRecordDTO.getStartCreateDate(), bookRecordDTO.getEndCreateDate(), bookRecordDTO.getStartCreateTime(),bookRecordDTO.getStartTime());
        Result<PageInfo<BookRecord>> result = pageService.queryBookRecordByDTO(bookRecordDTO);
        if (result.isFail()) {
            return new Result(ResultEnum.FAIL);
        }
        // 存放书籍信息
        PageInfo<BookRecord> data = result.getData();
        return new Result<>(ResultEnum.SUCCESS, data);
    }

    @RequestMapping("/downloadAndUpload/batchAddCreateDate")
    public @ResponseBody Result batchAddCreateDate(Model model) {
        log.info("开始一键添加创建时间");
        Result result = addService.batchAddCreateDate();
        String message = "添加数据成功";
        if (result.isFail()) {
            message = "添加数据失败";
            log.error("{}", message);
        }
        model.addAttribute(Constant.MESSAGE, message);
        return result;
    }

    /**
     * 添加书籍
     * @param bookRecordDTO
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/downloadAndUpload/addBookRecord")
    public String addBookRecord(@Valid BookRecordDTO bookRecordDTO, BindingResult bindingResult, Model model) {
        String forward = ForwardConstant.FORWARD_ADD_BOOK_RECORD;
        log.debug("绑定参数：{}", bindingResult);
        if (bindingResult.hasErrors()) {
            return BindingResultUtil.bindingResultHasError(bindingResult, model, forward);
        }
        // 检验数据
        String id = bookRecordDTO.getId();
        if (!RegularUtil.isPositiveIntegerNoSymbol(id)) {
            String errorMsg = "ID[" + id + "']不符合正整数格式类型，请检查";
            log.info("id[{}]不符合正整数格式类型，请检查", id);
            model.addAttribute(Constant.ERROR_MESSAGE, errorMsg);
            return forward;
        }
        BookRecord bookRecord = new BookRecord();
        bookRecord.setId(id);
        if (!StringUtil.isBlank(bookRecordDTO.getName())) {
            bookRecord.setName(bookRecordDTO.getName());
        }
        if (!StringUtil.isBlank(bookRecordDTO.getRemarks())) {
            bookRecord.setRemarks(bookRecordDTO.getRemarks());
        }
        log.info("开始添加{}:",CONTROLLER_NAME);
        Result result = addService.addBookRecord(bookRecord);
        if (result.isFail()) {
            String errorMsg = "添加数据失败";
            log.info("{}", errorMsg);
            model.addAttribute(Constant.ERROR_MESSAGE, errorMsg);
            return forward;
        }
        return forward;
    }

    @RequestMapping("/downloadAndUpload/deleteBookRecord")
    public String deleteBookRecord(@Valid BookRecordDTO bookRecordDTO, BindingResult bindingResult, Model model) {
        log.info("绑定参数：{}", bindingResult);
        if (bindingResult.hasErrors()) {
            return BindingResultUtil.bindingResultHasError(bindingResult, model, htmlRequestUrl);
        }
        log.info("校验书籍id：");
        // 删除数据id校验
        String id = bookRecordDTO.getId();
        if (!RegularUtil.isPositiveIntegerNoSymbol(id)) {
            String errorMsg = "ID[" + id + "']不符合正整数格式类型，不存在该数据，无法删除，请检查";
            log.info("id[{}]不符合正整数格式类型，请检查", id);
            model.addAttribute(Constant.ERROR_MESSAGE, errorMsg);
            return htmlRequestUrl;
        }
        log.info("开始删除{}:",CONTROLLER_NAME);

        Result result = addService.deleteBookRecord(id);
        if (result.isFail()) {
            String errorMsg = "删除据失败";
            log.info("{}", errorMsg);
            model.addAttribute(Constant.ERROR_MESSAGE, errorMsg);
            return htmlRequestUrl;
        }
        return htmlRequestUrl;
    }
}
