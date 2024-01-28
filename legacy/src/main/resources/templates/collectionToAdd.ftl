<#include "common.ftl">
<#include "menu.ftl">

<form method="Post">
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Your mem_id</label>
                <input name = "memId" type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
            </div>
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Your collection_id</label>
                <input name = "collectionId" type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
            </div>
        </div>
    </div>
    <div class="row">
        <input type="checkbox"  name="delete">
        <label for="delete"> delete</label><br>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>
