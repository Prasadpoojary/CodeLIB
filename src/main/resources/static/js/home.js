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
    
   
    select = $(".category input");
    
    
    $(select).on('focus',function(){
         $(this).attr('list','searchDataCont');
    });

  
    $(select).on('blur',function(){
        $(this).val('');
        $(this).attr('list','');
    });

    $(select).on('change',function(){
        var b=document.createElement('button');
        var a=document.createElement('a');
        $(a).attr('href','/category/book?category='+$(this).val());
        $(b).attr('class','catogorySearch');
        $(b).appendTo(a);
        $(a).appendTo('body');
        $('.catogorySearch').click();
    });
 
    $('.searchBtn').on('click',function(){
        $(".filterDiv a").attr('href','/search/book?keyword='+$('#search').val());
    });

    $('.bookCont').each(function(){
        $('.bookCont').delay(250);
        $(this).animate({'opacity':'1'},500);
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

    
    });
    