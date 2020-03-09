package pl.jazapp.app;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@WebServlet("average")
public class AverageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setContentType("text/plain");

        var numbers = req.getParameter("numbers");
        double sum = 0;
        double avg;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);

        String[] splitedNumbers = numbers.split(",");

        var writer = resp.getWriter();

        try {
            writer.println("I got '" + numbers + "'");
            for (int i = 0; i < splitedNumbers.length; i++){
                sum += Double.parseDouble(splitedNumbers[i]);
            }
            avg = sum/splitedNumbers.length;
            writer.println("Average equals: " + df.format(avg));
        } catch (NumberFormatException e) {
            writer.println("Haven't given any parameter to calculate the average");
        }

    }
}
