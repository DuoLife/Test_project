<script>
    seajs.use('qzact/common.m/imageLoader/index.js', function(loader){
        //预加载图片按钮
        var img_list = ['loading-imp.32','m-animate-1-imp.32','m-animate-2-imp.32','m-animate-3-imp.32'];
        //匹配高清屏
        if(window.devicePixelRatio>1.5){
            for(var i=0; i<img_list.length; i++){
                img_list[i] = 'http://qzonestyle.gtimg.cn/qz-act/vip/20141119-qzonev5/sprite/'+img_list[i]+"@2x.png?max_age=19830212&d=20141211211206";
            }
        }else{
            for(var j=0; j<img_list.length; j++){
                img_list[i] = 'http://qzonestyle.gtimg.cn/qz-act/vip/20141119-qzonev5/sprite/'+img_list[i]+".png?max_age=19830212&d=20141211211206";
            }
        }
        var btnGo = document.getElementById('J_btnGo');
        loader(img_list, function(o){
            document.getElementById('J_loadTest').innerHTML = (o*100)+'%';
            document.getElementById('J_loadProgress').style.width = (o*100)+'%';
            if(o === 1){
                //支持classList
                if(btnGo.classList&&btnGo.classList.remove){
                    btnGo.classList.remove('hide');
                }else{
                    var btnGoClass = btnGo.getAttribute('class');
                    btnGoClass = btnGoClass.replace(/hide/g, ' ');
                    btnGo.setAttribute('class', btnGoClass);
                }
                if(document.getElementById('J_loadArea')){
                    document.getElementById('J_loadArea').innerHTML = '';
                }
            }
        });
    });

    seajs.use(['./index', 'jquery', 'qzact.v8.lib'], function(mod, $, lib) {
        //loading完，进入主页
        var page = lib.storage.get('curPage');
        if(page && page!=1){    //登陆页面过来的 直接回到原先的页面
            $('#loading').hide();
            $('#fullPage').removeClass('hide');
            mod.init();
        }else{
            mod.init();
            $.fn.fullpage&&$.fn.fullpage.setAllowScrolling(false);
            $('#J_btnGo').click(function(e){
                e.preventDefault();
                $.fn.fullpage&&$.fn.fullpage.setAllowScrolling(true);
                $('.J_man').css({
                    transition: 'all 0.3s ease-in',
                    '-webkit-transition': 'all 0.3s ease-in',
                    transform: 'translateY(-400px)',
                    '-webkit-transform': 'translateY(-400px)'
                });
                $(this).remove();
                $('.J_star').remove();

                setTimeout(function(){
                    $('#loading').hide();
                    $('#fullPage').removeClass('hide');
                }, 600);
            });
        }
    });
</script>