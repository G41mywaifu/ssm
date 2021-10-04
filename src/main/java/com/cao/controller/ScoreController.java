
package com.cao.controller;

import com.github.pagehelper.PageInfo;
import com.cao.entity.*;
import com.cao.service.QuestionService;
import com.cao.service.SurveyService;
import com.cao.utils.MapControl;
import com.cao.utils.SessionUtils;
import com.cao.utils.SystemInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/create")
    public String v_create(){
        return "survey/add";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Survey survey, HttpServletRequest request){
        Admin currAdmin = SessionUtils.getAdmin(request);
        survey.setCreator(currAdmin.getId());
        survey.setState(Survey.state_create);
        survey.setAnon(survey.getAnon()!=null?0:1);
        int result = surveyService.create(survey);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = surveyService.deleteBatch(ids);
        if(result<=0){
         
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Survey survey){
        survey.setAnon(survey.getAnon()!=null?0:1);
        int result = surveyService.update(survey);
        if(result<=0){
           
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/list")
    public String list(){
       return  "survey/list";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Survey survey,HttpServletRequest request, ModelMap modelMap){


        Admin admin = SessionUtils.getAdmin(request);
        if(admin.getType() == 1){
            survey.setCreator(admin.getId());
        }

        List<Survey> list = surveyService.query(survey);
        
        for (Survey entity : list) {
             entity.setAdmin(SystemInit.adminMap.get(entity.getCreator()));
        }
        Integer count = surveyService.count(survey);
        return MapControl.getInstance().page(list,count).getMap();
    }


    @GetMapping("/detail")
    public String detail(Integer id,ModelMap modelMap){
        Survey survey = surveyService.detail(id);
        modelMap.addAttribute("survey",survey);
        return "survey/update";
    }

    @GetMapping("/question")
    public String question(Integer id,ModelMap modelMap){
        Survey survey = surveyService.detail(id);
        modelMap.addAttribute("survey",survey);
        return "survey/question";
    }

    @GetMapping("/preview/{id}")
    public String preview(@PathVariable("id") Integer id,ModelMap modelMap){
        Survey survey = surveyService.detail(id);
        Question question = new Question();
        question.setSurveyId(survey.getId());
        //查询一个问卷中的所有问题及选项
        List<Question> questions = questionService.query(question);
        //将问题设置为survey的属性
        survey.setQuestions(questions);
        modelMap.addAttribute("survey",survey);
        return "survey/preview";
    }

   

    @GetMapping("/my")
    public String my(){
        return  "survey/my";
    }

    @PostMapping("/my_query")
    @ResponseBody
    public Map<String,Object> my_query(@RequestBody Survey survey, ModelMap modelMap){
        PageInfo<Survey> pageInfo = surveyService.queryMySurvey(survey);
        Integer count = surveyService.count(survey);
        return MapControl.getInstance().page(pageInfo.getList(),Integer.parseInt(pageInfo.getTotal()+"")).getMap();
    }




}
