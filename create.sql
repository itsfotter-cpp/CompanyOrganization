
    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);

    create table company (
       company_id bigint not null auto_increment,
        address varchar(255),
        company_name varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table employees (
       id bigint not null auto_increment,
        cf varchar(255),
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        company_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table employees_roles (
       employee_id bigint not null,
        roles_id bigint not null
    ) engine=InnoDB;

    create table roles (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table employees 
       add constraint FK3dtsl9h3lnbeeqds8rm23cwja 
       foreign key (company_id) 
       references company (company_id);

    alter table employees_roles 
       add constraint FKcl8avellvioc3id4mokbhn27o 
       foreign key (roles_id) 
       references roles (id);

    alter table employees_roles 
       add constraint FKr9b8ry8qtdtoc8pcw56ug54x5 
       foreign key (employee_id) 
       references employees (id);
