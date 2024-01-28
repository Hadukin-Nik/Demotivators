<#include "common.ftl">
<#include "menu.ftl">

<form method="POST" enctype='multipart/form-data'>
    <div class="row">
        <input type="file" name="file" />
    </div>
    <div class="row">
        <input type="checkbox"  name="isCommentsAllowed">
        <label for="isCommentsAllowed"> Are comments allowed?</label><br>
    </div>

    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Description</label>
                <textarea  name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Tags</label>
                <textarea name="tags" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>

        </div>
    </div>

    <button type="submit" class="btn btn-primary">submit</button>
</form>

