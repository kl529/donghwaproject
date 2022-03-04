var main={
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
        });
        $('#btn-firstPage').on('click', function() {
            _this.firstPage();
        });
        $('#btn-prevPage').on('click', function(){
            _this.prevPage();
        });
        $('#btn-nextPage').on('click', function() {
            _this.nextPage();
        });
        $('#btn-review-save').on('click', function(){
            _this.review_save();
        });
    },

    save : function(){

        var form = $('bookContent')[0];
        var formData = new FormData(form);

          formData.append("title" , $('#title').val());
          formData.append("author" , $('#author').val());
          formData.append("publisher" , $('#publisher').val());
          formData.append("bookCover" , $('#bookCover').val());
          formData.append("totalPage" , $('#totalPage').val());
          formData.append("publishedDate" , $('#publishedDate').val());
          formData.append("country" , $('#country').val());
          formData.append("bookIntro" , $('#bookIntro').val());
        $.ajax({
            type: 'POST',
            url: '/api/v1/books/admin',
            contentType: false,
            processData : false,
            data: formData
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

    firstPage : function(){

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
            alert('첫번째 페이지 입니다.');
        }
    },

    nextPage : function() {
        var currentPage = Number(document.getElementById('currentPage').innerHTML);
        var totalPage = Number(document.querySelectorAll('.pages').length);
        var id = Number(document.getElementById('readingKey').innerHTML);

        var data = {
            option: 1
        };

        if (currentPage < totalPage) {
            $.ajax({
                type: 'PUT',
                url: '/api/v1/reading/calcpage/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                location.reload();
            })
        } else {
            alert('마지막 페이지 입니다.')
        }
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
