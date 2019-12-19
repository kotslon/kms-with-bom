# Maven BOM vs transitive dependencies demo

## 0. Project structure
* 3 KMs: `kmA`, `kmB`, `kmX` - each divided into `-api` and `-impl`.
* `kmA` and `kmB` use `kmX`.
* `war` uses `kmA` and `kmB` directly, thus it should use `kmX` indirectly. 
* `bom-api` and `bom-impl` define dependency management.
* Root pom.xml to compile them all.

## 1. Compile 1.0-SNAPSHOT
Compile all `1.0-SNAPSHOT` versions with `mvn clean install` to put them in your local maven repository.

## 2. Update X
Module `kmX-impl` releases new version:

Go to `./kmX-impl/pom.xml` and change version to `<version>2.0-SNAPSHOT</version>` on line 9. 

## 3. Use new version in either `km*`
Now we can change `kmX-impl` version in dependencies list of either `kmA-impl` or `kmB-impl` 
by uncommenting line 54: `<version>2.0-SNAPSHOT</version>`.

When we recompile root pom, we can verify, that nothing changes inside `war`: 
`/war/target/war-1.0-SNAPSHOT/WEB-INF/lib` always contains `kmx-impl-1.0-SNAPSHOT.jar` as prescribed by `bom-impl`.

## 4. Make it official
It's time to release the BOM. Go to the `bom-impl`'s pom.xml and change the property `kmx-impl.version` 
to `2.0-SNAPSHOT` (line 17)

Recompile the whole project and you can see `kmx-impl-2.0-SNAPSHOT.jar` inside `war`.

## 5. API
Do the same with `kmX-api`.

## 6. Even better
We can set `-impl` dependencies' scope to *runtime* in `bom-impl` and omit `<scope>runtime</scope>` tags in all pom files.
Try it by uncommenting scope in `bom-impl`.

## 7. Summary
* `-api`s should be used in *compile* scope (because we need them in compiler's classpath).
* `-impl`s should be used in *runtime* scope (because we need them to run, but want to forbid *direct* usage 
of implementation classes).
* `war`s should list direct dependencies and use common dependency management from BOMs to control versions
of direct and indirect dependencies.
* To enforce this policy we can set scope of all `-impl` dependencies to *runtime* in `bom-impl`.
