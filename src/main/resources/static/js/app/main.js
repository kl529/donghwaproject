var main={
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
        });
        $('#btn-prevPage').on('click', function(){
            _this.prevPage();
        });
        $('#btn-nextPage').on('click', function(){
            _this.nextPage();
        });
    },

    save : function(){
        var data ={
            title : $('#title').val(),
            author : $('#author').val(),
            publisher : $('#publisher').val(),
            country : $('#country').val(),
            bookIntro : $('#bookIntro').val(),
            bookCover : $('#bookCover').val(),
            bookContent : $('#bookContent').val(),
            totalPage : $('#totalPage').val(),
            publishedDate : $('#publishedDate').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/books/admin',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/books/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('책이 삭제되었습니다.');
            window.location.href = '/homepage';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },

    prevPage : function(){
        var currentPage = Number(document.getElementById('currentPage').innerHTML);
        var id = Number(document.getElementById('readingKey').innerHTML);

        var data = {
           option : 0
        };

        if(1 < currentPage){
            $.ajax({
                type: 'PUT',
                url : '/api/v1/reading/calcpage/'+id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function(){
                location.reload();
            })
        }else{
            alert('이전 페이지로 이동할 수 없습니다.');
        }
    },

    nextPage : function(){
        var currentPage = Number(document.getElementById('currentPage').innerHTML);
        var totalPage = Number( document.querySelectorAll('.pages').length );
        var id = Number(document.getElementById('readingKey').innerHTML);

        var data = {
           option : 1
        };

        if(currentPage < totalPage){
            $.ajax({
                type: 'PUT',
                url: '/api/v1/reading/calcpage/'+id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done( function(){
                location.reload();
            })
        }else{
            alert('다음 페이지로 이동할 수 없습니다.')
        }
    }
};

main.init();
