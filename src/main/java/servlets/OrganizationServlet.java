package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Organization;
import service.OrganizationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(name = "servlets.OrganizationServlet", value = "/organizations/*")
public class OrganizationServlet extends HttpServlet {
    private final OrganizationService service = new OrganizationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Set<Organization> organizations;
        organizations = service.getOrganizations();

        Gson gson = new Gson();
        String json = gson.toJson(organizations);

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        printWriter.write(json);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        Organization fromOrganization = new Gson().fromJson(sb.toString(), Organization.class);
        Organization toOrganization = new Organization(fromOrganization.getTitle(), fromOrganization.getAddress());

        Gson gson = new Gson();
        String json = null;
        Boolean inserted = service.addOrganization(toOrganization);

        if (inserted) {
            json = gson.toJson(toOrganization);
        } else {
            json = gson.toJson("Error");
            throw new IOException();
        }

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        printWriter.write(json);
        printWriter.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String id = pathParts[1];

        Gson gson = new Gson();
        String json = null;
        Boolean deleted = service.deleteOrganization(Long.parseLong(id));

        if (deleted) {
            json = gson.toJson("Successfully deleted");
        } else {
            json = gson.toJson("Error");
            throw new IOException();
        }

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        printWriter.write(json);
        printWriter.close();
    }
}
