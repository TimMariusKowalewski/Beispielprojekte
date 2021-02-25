#include <stdio.h>
#include <string.h>
#include <immintrin.h>

#include <math.h>

#include <CL/cl.h>

#define DEFAULT_CHAR_ARR_SIZE 50

#define MEM_SIZE (128)
#define MAX_SOURCE_SIZE (0x100000)

void f(int *ptr1, int *ptr2);
void swap_pointer_values(int *ptr1, int *ptr2);

struct list_element
{
    struct list_element *next;
    struct list_element *previous;
    char name[DEFAULT_CHAR_ARR_SIZE];
    void *data;
    char data_type;
};

struct linked_list
{
    struct list_element *head;
    struct list_element *tail;
    char name[DEFAULT_CHAR_ARR_SIZE];
    int count_elements;
    int max_elements;
};

int linked_list_count(struct linked_list *List)
{
    if (List != NULL)
    {
        if (List->head == NULL)
        {
            return 0;
        }
        else
        {
            struct list_element *_current_element = NULL;
            _current_element = List->head;

            int _counter = 0;
            while (_current_element != NULL)
            {
                _counter++;
                _current_element = _current_element->next;
            }

            return _counter;
        }
    }
    else
    {
        return 0;
    }
}

// Element am Ende anhängen
void linked_list_add(struct linked_list *List, struct list_element *Element)
{
    if (linked_list_count(List) == 0)
    {
        List->head = Element;
        List->tail = Element;
    }
    else
    {
        List->tail->next = Element;
        Element->previous = List->tail;
        List->tail = Element;
    }
}

// Element am Anfang einfügen
void linked_list_insert(struct linked_list *List, struct list_element *Element)
{
    if (linked_list_count(List) == 0)
    {
        List->head = Element;
        List->tail = Element;
    }
    else
    {
        Element->next = List->head;
        List->head->previous = Element;
        List->head = Element;
    }
}

// die Position von 2 Elementen tauschen
void linked_list_swap_elements(struct linked_list *List, struct list_element *Element1, struct list_element *Element2)
{
    if (List != NULL)
    {
        // Prüfung, ob die Elemente nebeneinander liegen
        if (Element1->next == Element2 || Element2->next == Element1)
        {
            printf("Elemente liegen nebeneinander\n");

            // wir brauchen das Element vor Element1 (ggf. NULL)
            if (Element1->previous != NULL)
            {
                Element1->previous->next = Element2;
            }

            // Element1 aktualisieren
            Element1->next = Element2->next;
            struct list_element *_element1_old_previous = Element1->previous;
            Element1->previous = Element2;

            // Element2 aktualisieren
            Element2->next = Element1;
            Element2->previous = _element1_old_previous;

            // wir brauchen das Element nach Element2 (ggf. NULL)
            if (Element1->next != NULL)
            {
                Element1->next->previous = Element1;
            }
        }
        else
        {
            printf("Elemente liegen nicht nebeneinander\n");
        }
    }
}

void linked_list_reverse(struct linked_list *List)
{
}

void linked_list_split(struct linked_list *List)
{
}

void linked_list_info(struct linked_list *List)
{
    if (List->head != NULL && List->tail != NULL)
    {
        printf("Aktueller Kopf der Liste: %s\n", List->head->name);
        printf("Aktueller Fuss der Liste: %s\n", List->tail->name);
        printf("Anzahl Elemente: %d\n", linked_list_count(List));
    }
    else
    {
        printf("Aktueller Kopf der Liste: Leere Liste\n");
        printf("Aktueller Fuss der Liste: Leere Liste\n");
        printf("Anzahl Elemente: Leere Liste\n");
    }
}

void linked_list_output(struct linked_list *List)
{
    if (List != NULL)
    {
        struct list_element *_current_element = List->head;

        int _counter = 0;
        while (_current_element != NULL)
        {
            _counter++;

            char _prev[DEFAULT_CHAR_ARR_SIZE];
            char _next[DEFAULT_CHAR_ARR_SIZE];
            if (_current_element->previous != NULL)
            {
                memcpy(_prev, _current_element->previous->name, sizeof(_current_element->previous->name));
            }
            else
            {
                strncpy(_prev, "NULL", 4);
            }
            if (_current_element->next != NULL)
            {
                memcpy(_next, _current_element->next->name, sizeof(_current_element->next->name));
            }
            else
            {
                strncpy(_next, "NULL", 4);
            }
            printf("%d. Element %s - prev:%s, next: %s\n", _counter, _current_element->name, _prev, _next);

            _current_element = _current_element->next;
        }
    }
}

int main(int argc, char **argv, char **env)
{
    test_opencl();
    return 0;

    int i = 0;
    printf("argc: %d\n", argc);
    for (i = 0; i < argc; i++)
    {
        printf("argv[%d]: %s\n", i, argv[i]);
    }

    i = 0;
    while (env[i] != NULL)
    {
        printf("env[%d]: %s\n", i, env[i]);
        i++;
    }

    return 0;
}

void test1()
{
    printf("Hello world");
    int a = 23;
    int b = 42;
    int *pa = &a;
    int *pb = &b;

    struct permissions
    {
        unsigned char owner : 3;
        unsigned char group : 3;
        unsigned char others : 3;
    };

    printf("Pointer - zeigt auf Adresse: %p\n", &pa);
    printf("Pointer - zeigt auf Adresse: %p\n", &pb);
    f(pa, pb);
    swap_pointer_values(pa, pb);
    f(pa, pb);

    struct list_element elem1 = {NULL, NULL, "asd1"};
    struct list_element elem2 = {NULL, NULL, "asd2"};
    struct list_element elem3 = {NULL, NULL, "asd3"};
    struct list_element elem4 = {NULL, NULL, "asd4"};
    struct list_element elem5 = {NULL, NULL, "asd5"};
    struct linked_list list = {NULL, NULL, "Liste1"};
    linked_list_info(&list);
    linked_list_add(&list, &elem1);
    linked_list_info(&list);
    linked_list_add(&list, &elem2);
    linked_list_add(&list, &elem3);
    linked_list_add(&list, &elem4);
    linked_list_insert(&list, &elem5);
    linked_list_info(&list);
    linked_list_output(&list);
    linked_list_swap_elements(&list, &elem2, &elem3);
    linked_list_output(&list);
    linked_list_swap_elements(&list, &elem3, &elem2);
    linked_list_output(&list);

    int **myintptr;

    struct permissions myperms;
    myperms.owner = 7;
    myperms.group = 5;
    myperms.others = 5;

    printf("%d%d%d\n", myperms.owner, myperms.group, myperms.others);
}

void f(int *ptr1, int *ptr2)
{
    printf("Pointer1 - Adresse: %p\n", ptr1);
    printf("Pointer1 - zeigt auf Adresse: %p\n", &ptr1);
    printf("Pointer1 - Wert hinter der Adresse: %d\n", *ptr1);
    printf("Pointer2 - Adresse: %p\n", ptr2);
    printf("Pointer2 - zeigt auf Adresse: %p\n", &ptr2);
    printf("Pointer2 - Wert hinter der Adresse: %d\n", *ptr2);
}

void swap_pointer_values(int *ptr1, int *ptr2)
{
    int tmp = *ptr1;
    *ptr1 = *ptr2;
    *ptr2 = tmp;
}

void test_opencl()
{
    cl_device_id device_id = NULL;
    cl_context context = NULL;
    cl_command_queue command_queue = NULL;
    cl_mem memobj = NULL;
    cl_program program = NULL;
    cl_kernel kernel = NULL;
    cl_platform_id platform_id = NULL;
    cl_uint ret_num_devices;
    cl_uint ret_num_platforms;
    cl_int ret;

    char string[MEM_SIZE];

    FILE *fp;
    char fileName[] = "./hello.cl";
    char *source_str;
    size_t source_size;

    /* Load the source code containing the kernel*/
    fp = fopen(fileName, "r");
    if (!fp)
    {
        fprintf(stderr, "Failed to load kernel.\n");
        exit(1);
    }
    source_str = (char *)malloc(MAX_SOURCE_SIZE);
    source_size = fread(source_str, 1, MAX_SOURCE_SIZE, fp);
    fclose(fp);

    /* Get Platform and Device Info */
    //ret = clGetPlatformIDs(1, &platform_id, &ret_num_platforms);
    ret = clGetPlatformIDs(0, NULL, &ret_num_platforms);
    printf("%d\n", ret);
    ret = clGetDeviceIDs(platform_id, CL_DEVICE_TYPE_DEFAULT, 1, &device_id, &ret_num_devices);
    //printf("%d\n", ret);

    /* Create OpenCL context */
    context = clCreateContext(NULL, 1, &device_id, NULL, NULL, &ret);

    /* Create Command Queue */
    command_queue = clCreateCommandQueue(context, device_id, 0, &ret);

    /* Create Memory Buffer */
    memobj = clCreateBuffer(context, CL_MEM_READ_WRITE, MEM_SIZE * sizeof(char), NULL, &ret);

    /* Create Kernel Program from the source */
    program = clCreateProgramWithSource(context, 1, (const char **)&source_str,
                                        (const size_t *)&source_size, &ret);

    /* Build Kernel Program */
    ret = clBuildProgram(program, 1, &device_id, NULL, NULL, NULL);

    /* Create OpenCL Kernel */
    kernel = clCreateKernel(program, "hello", &ret);

    /* Set OpenCL Kernel Parameters */
    ret = clSetKernelArg(kernel, 0, sizeof(cl_mem), (void *)&memobj);

    /* Execute OpenCL Kernel */
    ret = clEnqueueTask(command_queue, kernel, 0, NULL, NULL);

    /* Copy results from the memory buffer */
    ret = clEnqueueReadBuffer(command_queue, memobj, CL_TRUE, 0,
                              MEM_SIZE * sizeof(char), string, 0, NULL, NULL);

    /* Display Result */
    puts(string);

    /* Finalization */
    ret = clFlush(command_queue);
    ret = clFinish(command_queue);
    ret = clReleaseKernel(kernel);
    ret = clReleaseProgram(program);
    ret = clReleaseMemObject(memobj);
    ret = clReleaseCommandQueue(command_queue);
    ret = clReleaseContext(context);

    free(source_str);
}