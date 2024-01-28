
<#include "common.ftl">

<html>
<body>

<form method="Post" action="" enctype="multipart/form-data">
    <div class="row">
        <div class="col-md-2">
            <input name="login" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="Login">
        </div>
        <div class="col-md-2">
            <input name="password" type="password" class="form-control" id="exampleInputPassword1"
                   placeholder="Password">
        </div>
    </div>
    <div class = "row">
        <div class="col-md-2">
            <div class="form-group">
                <input name="name" type="text" class="form-control" id="exampleInputPassword1" placeholder="Name">
            </div>
        </div>

        <div class="col-md-2">
            <div class="form-group">
                <input name="nickname" type="text" class="form-control" id="exampleInputPassword1" placeholder="Nickname">
            </div>
        </div>
    </div>

    <input name="file" type="file" class="form-control" id="exampleInputPassword1" placeholder="Avatar">

    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<a href="${address}">Back</a>

</body>
</html>