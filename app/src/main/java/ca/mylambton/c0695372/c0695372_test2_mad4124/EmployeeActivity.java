package ca.mylambton.c0695372.c0695372_test2_mad4124;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.mylambton.c0695372.c0695372_test2_mad4124.model.Employee;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class EmployeeActivity extends AppCompatActivity {

    EditText mId;
    EditText mName;
    EditText mBirthDate;
    EditText mSalary;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        setTitle("Employee Entry form");

        mId = (EditText) findViewById(R.id.eId);
        mName = (EditText) findViewById(R.id.eName);
        mBirthDate = (EditText) findViewById(R.id.eBirthDate);
        mSalary = (EditText) findViewById(R.id.eSalary);

        saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String employeeId = mId.getText().toString();
                if (employeeId.isEmpty()) {
                    Toast.makeText(EmployeeActivity.this, "Please inform the employee's id.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String employeeName = mName.getText().toString();
                if (employeeName.isEmpty()) {
                    Toast.makeText(EmployeeActivity.this, "Please inform the employee's name.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String employeeBirthDate = mBirthDate.getText().toString();
                if (employeeBirthDate.isEmpty()) {
                    Toast.makeText(EmployeeActivity.this, "Please inform the employee's birth date.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String employeeSalary = mSalary.getText().toString();
                if (employeeSalary.isEmpty() || Double.parseDouble(employeeSalary) <= 0) {
                    Toast.makeText(EmployeeActivity.this, "Please inform the employee's salary.", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Employee employee = new Employee();
                employee.setId(employeeId);
                employee.setName(employeeName);
                employee.setBirthDate(employeeBirthDate);
                employee.setSalary(Double.parseDouble(employeeSalary));

                Realm realm = Realm.getDefaultInstance();

                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealm(employee);
                        }
                    });
                    clearAllFields();
                    Toast.makeText(EmployeeActivity.this, "Employee saved successfully", Toast.LENGTH_SHORT).show();
                } catch (RealmPrimaryKeyConstraintException e) {
                    Toast.makeText(EmployeeActivity.this, "There is already an employee using the id " + (employeeId) + ".", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void clearAllFields() {
        mId.setText("");
        mName.setText("");
        mBirthDate.setText("");
        mSalary.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem employeesList = menu.findItem(R.id.list);
        employeesList.setVisible(true);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.list:
                Intent empListIntent = EmployeesListActivity.newIntent(this);
                startActivity(empListIntent);

                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
































