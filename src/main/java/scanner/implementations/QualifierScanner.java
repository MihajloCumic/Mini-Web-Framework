package scanner.implementations;

import annotations.Qualifier;
import scanner.PackageScanner;


import java.util.Set;

public class QualifierScanner extends PackageScanner {

    public QualifierScanner(String packageName) {
        super(packageName);
    }

    @Override
    public Set<Class<?>> findAnnotatedClassesInPackage() {
        return this.findAnnotatedClassesInPackage(Qualifier.class);
    }
}
