var main={
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
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
            alert('책이 삭제되었습니다.')
            window.location.href = '/homepage';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();
