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


        double sum = 0;
        double avg;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);



        var writer = resp.getWriter();

        try {
            var numbers = req.getParameter("numbers");
            String[] splitedNumbers = numbers.split(",");
            for (int i = 0; i < splitedNumbers.length; i++){
                sum += Double.parseDouble(splitedNumbers[i]);
            }
            avg = sum/splitedNumbers.length;
            writer.println("Average equals: " + df.format(avg));
        } catch (NullPointerException | NumberFormatException e) {
            writer.println("Please put parameters.");
        }

    }
}
