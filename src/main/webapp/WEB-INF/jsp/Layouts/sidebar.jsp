<div class="col-sm-3 col-md-2 sidebar">
    <%
        String uri = request.getRequestURI();
        String activeMenu = "TASK";
        if (uri.contains("/User/")) {
            activeMenu = "USER";
        } else if (uri.contains("/Role/")) {
            activeMenu = "ROLE";
        } else if (uri.contains("/Lookup/")) {
            activeMenu = "LOOKUP";
        } else if (uri.contains("/Document/")) {
            activeMenu = "DOCUMENT";
        }else if (uri.contains("/Organization/")) {
            activeMenu = "ORGANIZATION";
        }

    %>
    <ul class="nav nav-sidebar">
        <li
                <% if ("TASK".equals(activeMenu)) {%>
                class="active"
                <%}%>
                >
            <a href="../Task/showMyTaskList">
                <i class="fa fa-tasks fa-fw"></i>
                My Task</a>
        </li>

    </ul>
    <ul class="nav nav-sidebar">
        <li
                <% if ("DOCUMENT".equals(activeMenu)) { %>
                class="active"
                <%}%>
                ><a href="../Document/showDocumentList"><i class="fa fa-cogs fa-fw"></i> Document</a></li>


        <li
                <% if ("USER".equals(activeMenu)) {%>
                class="active"
                <%}%>
                ><a href="../User/showUserList"><i class="fa fa-user fa-fw"></i> User</a></li>
        <li
                <% if ("ROLE".equals(activeMenu)) {%>
                class="active"
                <%}%>
                ><a href="../Role/showRoleList"><i class="fa fa-user-md fa-fw"></i> Role</a></li>
        <li
                <% if ("LOOKUP".equals(activeMenu)) {%>
                class="active"
                <%}%>
                ><a href="../Lookup/showLookupTypeList"><i class="fa fa-tags fa-fw"></i> Lookups</a></li>
        <li
                <% if ("ORGANIZATION".equals(activeMenu)) {%>
                class="active"
                <%}%>
                ><a href="../Organization/showMasterOrganizationList"><i class="fa fa-envelope fa-fw"></i> Organization</a></li>
    </ul>

</div>