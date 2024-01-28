<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">MyMemes.co</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link <#if URL == toScroll>active</#if>" href="${toScroll}">Home</a>
            <a class="nav-item nav-link <#if URL == toFriendsScroll>active</#if>" href="${toFriendsScroll}">Friends Scroll</a>
            <a class="nav-item nav-link <#if URL == toMemUpload>active</#if>" href="${toMemUpload}">Meme upload</a>
            <a class="nav-item nav-link <#if URL == toRequest>active</#if>" href="${toRequest}">Requests</a>
            <a class="nav-item nav-link <#if URL == toCollections>active</#if>" href="${toCollections}">Collection</a>
            <a class="nav-item nav-link <#if URL == toAddCollections>active</#if>" href="${toAddCollections}">Add to collection</a>
            <a class="nav-item nav-link <#if URL == toFriends>active</#if>" href="${toFriends}">Friends</a>
            <#if isAdmin>
                <a class="nav-item nav-link" href="${toUsers}">Users</a>
            </#if>
        </div>
    </div>
</nav>