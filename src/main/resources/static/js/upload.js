$(window).on("load",function(){
    $(".loading").css("display","none");

    var msg=$(".msgCont");
    var msgCloseBtn=$(".msgCloseBtn");
    if(msg.html())
    {
        $(".msgBase").css("display","block");
    }
    else
    {
        $(".msgBase").css("display","none");
    }
    
    msgCloseBtn.on("click",function(){
        $(".msgBase").css("display","none");
    });

    $(".uucategory").each(function(){
        if($(this).html().toUpperCase()=="E-BOOK")
        {
            $(this).css("background","#110168");
        }
        else
        {
            $(this).css("background","#a1013f");
        }
    });

    $('.uu').each(function(){
        $('.uu').delay(250);
        $(this).animate({'opacity':'1'},500);
    });

    $(".select").on("click",function(){
        $(".file").click();
    });

    $(".ebookBtn").on("click",function(){
        $(".uploadBtnDiv").css("display","none");
        $(".upBookForm").css("display","block");
    });
    
    $(".courseBtn").on("click",function(){
        $(".uploadBtnDiv").css("display","none");
        $(".upCourseForm").css("display","block");
    });

    $(".menuToggle").on("click",function(){
        $(".nav").css("display","block")
        .animate({"opacity":"1"},250);
     });
 
     $(".navCloseBtn").on("click",function(){
         $(".nav").animate({"opacity":"0"},150,disNoneNav);
      });
 
     function disNoneNav()
     {
         $(".nav").css("display","none");
     }

     
     $('#formCtgry').on('focus',function(){
        $(this).attr("list","formCategory");
    });
    $('#formCourseCtgry').on('focus',function(){
       $(this).attr("list","formCourseCategory");
   });

   
   var duu=$(".uu");
   var duuLen=duu.length;
   $(duu[0]).css("border-radius",'8px 8px 0px 0px');
   $(duu[duuLen-1]).css("border-radius",'0px 0px 8px 8px');
    
    });
    