finatra-servlet
===============

Finatra servlet is an adapter to allow Finagle based applications including Finatra-based applications into a servlet container that supports either Jetty Continuations or the Servlet API 3.0.

<a name="Quick Start"></a>

### Quick Start
---------------

#### Jetty style continuations

Paste the following block of code into your web.xml file to use Jetty style continuations.  Your-Service-Factory should be the fully qualified name of a class that implements ServiceFactory and has a zero-arg constructor.

<pre>
&lt;web-app&gt;
  &lt;-- define a FinagleJettyServlet to use Jetty style continuations -&gt;
  &lt;servlet&gt;
    &lt;servlet-name&gt;jetty&lt;/servlet-name&gt;
    &lt;servlet-class&gt;com.github.savaki.finagle.servlet.jetty.FinagleJettyServlet&lt;/servlet-class&gt;
    &lt;init-param&gt;
      &lt;param-name&gt;service.factory.classname&lt;/param-name&gt;
      &lt;param-value&gt;com.github.savaki.finagle.servlet.EmptyServiceFactory&lt;/param-value&gt;
    &lt;/init-param>
  &lt;/servlet>
&lt;/web-app&gt;
</pre>

#### Servlet API 3.0

For a Servlet 3.0 container and support for AsyncContext, paste the following code block into your web.xml.  Again, you'll want to replace Your-Service-Factory with the name of the service factory that you define.

<pre>
&lt;web-app&gt;
  &lt;-- define a FinagleAsyncServlet to use the Servlet API 3.0 -&gt;
  &lt;servlet&gt;
    &lt;servlet-name&gt;async&lt;/servlet-name&gt;
    &lt;servlet-class&gt;com.github.savaki.finagle.servlet.servlet_30.FinagleAsyncServlet&lt;/servlet-class&gt;
    &lt;init-param&gt;
      &lt;param-name&gt;service.factory.classname&lt;/param-name&gt;
      &lt;param-value&gt;com.github.savaki.finagle.servlet.EmptyServiceFactory&lt;/param-value&gt;
    &lt;/init-param>
  &lt;/servlet>
&lt;/web-app&gt;
</pre>

### Service Factory
-------------------

To register services with the FinagleServlet, you'll need to implement the com.github.savaki.finagle.servlet.ServiceFactory interface.  

#### FinatraServiceFactory

To use Finatra Controllers and Filters with the finagle-servlet adapter, you can implement a class as follows:

<pre>
package your.pkg

class MyFinatraAdapter extends FinatraServiceFactory {
  // add your controllers 
  register(new MyController())
  register(new Anotherontroller())
  
  // and optionally add any filters that you may want
  addFilter(new MyFilter())
}
</pre>

In your web.xml file, you can use you.pkg.MyFinatraAdapter in place of the value for *service.factory.classname*

### ServiceFactory

For finagle projects that don't use finatra, you can simply implement the ServiceFactory interface to bind any Service[Request, Response] to a servlet container.