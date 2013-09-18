stormgen
========

An Eclipse tool to generate entire storm topology implementations (ex business logic)

Input to the tool is a simple XML model listing the spouts, the bolts and the streams that connect them.

The tool itself is a JET transform that generates:

- A maven-enabled Java project
- A fully implemented topology class
- Bolt and Spout classes configured to emit to and read from streams
- Beans that fully encapsulate understanding of the streams (field name, type and order)
- Logic classes for bolts and spouts, factored out and referenced by their respective component classes
- Test classes for each spout, bolt and bean
- An input file for graphiz that visualizes the complete topology

There are well-defined places in the generated code where changes made after generation will be retained through subsequent generations.  This means that you can make several kinds of changes to your topology and regenerate the code, keeping any business logic already typed in. 
