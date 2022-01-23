var main={
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
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
        },

        $.ajax({
            type: 'POST',
            url: '/api/v1/books/admin',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/homepage';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();