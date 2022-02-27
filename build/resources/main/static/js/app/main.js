var main={
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
        });
        $('#btn-review-save').on('click', function(){
            _this.review_save();
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
            alert('책이 삭제되었습니다.')
            window.location.href = '/homepage';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    review_save : function(){
        var data ={ //여기에 데이터 id랑 ReadingUpdateRequestDto를 넣어줘야함 // Bookkey도 어디선가 가져와야함
            bookReport : $('#review').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/reading/savebookreport/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('독후감이 등록되었습니다.');
            window.location.href = '/book/info/'+id;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();
