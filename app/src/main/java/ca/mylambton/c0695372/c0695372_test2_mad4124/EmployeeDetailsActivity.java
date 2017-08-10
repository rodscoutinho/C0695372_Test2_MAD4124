package ca.mylambton.c0695372.c0695372_test2_mad4124;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.mylambton.c0695372.c0695372_test2_mad4124.model.Employee;
import io.realm.Realm;

public class EmployeeDetailsActivity extends AppCompatActivity {

    TextView mId;
    TextView mName;
    TextView mBirthDate;
    TextView mSalary;
    TextView mFederalTax;
    TextView mProvinceTax;
    TextView mNetSalary;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EmployeeDetailsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        String employeeId = getIntent().getStringExtra(EmployeesListActivity.EXTRA_EMPLOYEE);

        Realm realm = Realm.getDefaultInstance();
        Employee employee = realm.where(Employee.class).equalTo("id", employeeId).findFirst();

        setTitle("Employee Details");

        mId = (TextView) findViewById(R.id.empId);
        mName = (TextView) findViewById(R.id.empName);
        mBirthDate = (TextView) findViewById(R.id.empBirthdate);
        mSalary = (TextView) findViewById(R.id.empSalary);
        mFederalTax = (TextView) findViewById(R.id.empFederalTax);
        mProvinceTax = (TextView) findViewById(R.id.empProvinceTax);
        mNetSalary = (TextView) findViewById(R.id.empNetSalary);

        mId.setText("Id: " + employee.getId());
        mName.setText("Name: " + employee.getName());
        mBirthDate.setText("Birthdate: " + employee.getBirthDate());
        mSalary.setText("Gross salary: " + String.format("%.2f", employee.getSalary()));

        Double federalTax = employee.getSalary() * 0.15;
        Double provinceTax = employee.getSalary() * 0.05;
        mFederalTax.setText("15% Federal Tax: " + String.format("%.2f", federalTax));
        mProvinceTax.setText("5% Province Tax: " + String.format("%.2f", provinceTax));
        mNetSalary.setText("Net Salary: " + String.format("%.2f", employee.getSalary() - federalTax - provinceTax));
    }
}
