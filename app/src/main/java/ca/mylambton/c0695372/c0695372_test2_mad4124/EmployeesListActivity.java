package ca.mylambton.c0695372.c0695372_test2_mad4124;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.mylambton.c0695372.c0695372_test2_mad4124.model.Employee;
import io.realm.Realm;
import io.realm.RealmResults;

public class EmployeesListActivity extends AppCompatActivity {

    public static final String EXTRA_EMPLOYEE = "ca.mylambton.c0695372.c0695372_test2_mad4124.employeeId";

    ListView empList;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EmployeesListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        setTitle("List of employees");

        empList = (ListView) findViewById(R.id.employeesList);

        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Employee> employees = realm.where(Employee.class).findAll();

        final ArrayList<Employee> employeesList = new ArrayList<>();
        for (Employee emp: employees) {
            employeesList.add(emp);
        }

        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, employeesList);

        empList.setAdapter(adapter);

        empList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee employee = employeesList.get(i);
                Intent employeeDetailsIntent = EmployeeDetailsActivity.newIntent(EmployeesListActivity.this);
                employeeDetailsIntent.putExtra(EXTRA_EMPLOYEE, employee.getId());
                startActivity(employeeDetailsIntent);
            }
        });

    }

}
